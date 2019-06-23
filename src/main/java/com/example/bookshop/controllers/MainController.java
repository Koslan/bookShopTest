package com.example.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.repositories.GenreRepository;
import com.example.bookshop.repositories.OrderRepository;
import com.example.bookshop.repositories.UserRepository;
import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Genre;
import com.example.bookshop.models.Order;
import com.example.bookshop.models.User;

@Controller
public class MainController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/")
	private String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("contentPage", "books");
		return "index";
	}

	@GetMapping("/books/bookDetails/{id}")
	private String showBook(@PathVariable("id") Integer id, Model model) {
		Book book = bookRepository.getOne(id);
		model.addAttribute("book", book);
		model.addAttribute("contentPage", "bookDetails");
		return "index";
	}

	/**
	 * 
	 * @param book
	 * @return Добавляет книгу в корзину пользователя
	 */
	@PostMapping("/books/bookDetails/{id}")
	public String addBookToBasket(@ModelAttribute("book") Book book, @ModelAttribute("order") Order order) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findById(auth.getName()).orElse(new User());
		order.setUser(user);
		orderRepository.save(order);
		return "redirect:/";
	}

	@GetMapping("/books/editBook/{id}")
	private String editBook(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("book", bookRepository.getOne(id));
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("genreChecked", bookRepository.getOne(id).getGenres());
		model.addAttribute("authorsChecked", bookRepository.getOne(id).getAuthors());

		model.addAttribute("book", bookRepository.getOne(id));
		model.addAttribute("contentPage", "editBook");
		return "index";

	}

	@PostMapping("/books/editBook")
	private String editBookSubmit(@ModelAttribute("book") Book book) {
		bookRepository.saveAndFlush(book);
		return "redirect:/";
	}

	@GetMapping("/books/addBook")
	private String addBook(Model model) {
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("contentPage", "addBook");
		return "index";

	}

	@PostMapping("/books/addBook")
	private String addBookSubmit(@ModelAttribute("book") Book book) {
		bookRepository.save(book);
		return "redirect:/";
	}

	/**
	 * Возвращает страницу со всеми книгами по жанру
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/genres/{id}")
	private String getBookByGenres(@PathVariable("id") Integer id, Model model) {
		List<Book> allBooks = bookRepository.findAll();
		List<Book> booksSortedByGenre = new ArrayList<Book>();

		for (Book book : allBooks) {
			for (Genre genre : book.getGenres()) {
				Integer genreId = genre.getId();
				if (genreId.equals(id)) {
					booksSortedByGenre.add(book);
				}
			}
		}
		model.addAttribute("books", booksSortedByGenre);
		model.addAttribute("contentPage", "genres");
		return "index";
	}

	/**
	 * Возвращает страницу со всеми книгами автора
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/authors/{id}")
	private String getBookByAuthors(@PathVariable("id") Integer id, Model model) {
		List<Book> allBooks = bookRepository.findAll();
		List<Book> booksSortedByAuthor = new ArrayList<Book>();

		for (Book book : allBooks) {
			for (Author author : book.getAuthors()) {
				Integer authorId = author.getId();
				if (authorId.equals(id)) {
					booksSortedByAuthor.add(book);
				}
			}
		}
		model.addAttribute("books", booksSortedByAuthor);
		model.addAttribute("contentPage", "authors");
		return "index";
	}

	@GetMapping("/authors/add")
	private String getAddAuthor(@ModelAttribute Author author, Model model) {
		model.addAttribute("contentPage", "addAuthor");
		return "index";
	}

	@PostMapping("/authors/add")
	private String addAuthor(@ModelAttribute Author author) {
		boolean isEmty = true;
		List<Author> authors = authorRepository.findAll();
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getName().equals(author.getName())) {
				isEmty = false;
			}
		}
		if (isEmty) {
			authorRepository.saveAndFlush(author);
			return "redirect:/books/addBook";
		} else {
			return "redirect:/authors/add";
		}
	}

	@GetMapping("/genres/add")
	private String getGenresAdd(@ModelAttribute Genre genre, Model model) {
		model.addAttribute("contentPage", "genreAdd");
		return "index";
	}

	@PostMapping("/genres/add")
	private String genresAdd(@ModelAttribute Genre genre) {
		boolean isEmty = true;
		List<Genre> genres = genreRepository.findAll();
		for (int i = 0; i < genres.size(); i++) {
			if (genres.get(i).getName().equals(genre.getName())) {
				isEmty = false;
			}
		}
		if (isEmty) {
			genreRepository.saveAndFlush(genre);
			return "redirect:/books/addBook";
		} else {
			return "redirect:/genres/add";
		}
	}
}