package com.example.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookshop.models.Book;
import com.example.bookshop.models.Order;
import com.example.bookshop.models.User;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.repositories.GenreRepository;
import com.example.bookshop.repositories.OrderRepository;
import com.example.bookshop.repositories.UserRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/books/bookDetails/{id}")
	private String showBook(@PathVariable("id") Integer id, Model model) {
		Book book = bookRepository.getOne(id);
		model.addAttribute("book", book);
		model.addAttribute("contentPage", "bookDetails");
		return "index";
	}

	/**
	 * Заказ книги
	 * @param book
	 * @return Добавляет книгу в корзину пользователя
	 */
	@PostMapping("/books/bookDetails")
	public String addBookToBasket(@ModelAttribute("book") Book book, @ModelAttribute("order") Order order) {
		order.setBookName(book.getName());
		orderRepository.saveAndFlush(order);
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
}
