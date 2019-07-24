package com.example.bookshop.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookshop.models.Book;
import com.example.bookshop.models.BookDTO;
import com.example.bookshop.models.Genre;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.repositories.GenreRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
 

@Controller
public class OptionalController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private GenreRepository genreRepository;

	@GetMapping("/optional")
	private String getPage(Model model) {
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("contentPage", "optional");
		return "index";
	}

	/**
	 * This method give books in choosed genre
	 * 
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/checkGenre", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
	public @ResponseBody String checkGenre(@RequestParam String genres) {
		Gson gson = new Gson();

		return gson.toJson(getBookForGenre(genres));
		
		
		 
	}
	
	
	private ArrayList getBookForGenre(String genres) {
		ArrayList list = new ArrayList<>();
		ArrayList<BookDTO> books = new ArrayList<>();
		
		Integer g;
		if (genres != null)
			g = Integer.parseInt(genres);
		else
			g = 1;
		
		Genre genre = genreRepository.findById(g).orElse(null);
		
		
		for (Book book : bookRepository.findAll()) {
			if(book.getGenres().contains(genre)) {
			BookDTO bookDto = new BookDTO();
			bookDto.setId(book.getId());
			bookDto.setName(book.getName());
			books.add(bookDto);
			}
		}
		
		
		return books;
	}

}
