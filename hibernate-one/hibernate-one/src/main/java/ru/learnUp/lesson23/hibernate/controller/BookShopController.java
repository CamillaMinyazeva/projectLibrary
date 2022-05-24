package ru.learnUp.lesson23.hibernate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.services.AuthorService;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/bookshop")
public class BookShopController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookShopController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/books")
    public String books(Model model) {

        List<Book> books = bookService.getBooks();

        model.addAttribute(
                "books",
                books
        );
        return "books";
    }

    @GetMapping("/authors")
    public String authors(Model model) {

        List<Author> authors = authorService.getAuthors();

        model.addAttribute(
                "authors",
                authors
        );
        return "authors";
    }



    // http://localhost:8080/bookshop/
    // http://localhost:8080/bookshop/books
    // http://localhost:8080/bookshop/authors
}