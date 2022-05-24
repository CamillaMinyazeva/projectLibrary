package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.filters.BookFilter;
import ru.learnUp.lesson23.hibernate.dao.repository.BookRepository;
import ru.learnUp.lesson23.hibernate.exceptions.NameAlreadyExists;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.learnUp.lesson23.hibernate.dao.specifications.BookSpecification.byFilter;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book createBook(Book book) {
        if (bookRepository.getBookByName(book.getName()) != null) {
            throw new NameAlreadyExists("This name already exists");
        }
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksBy(BookFilter filter) {
        Specification<Book> specification = where(byFilter(filter));
        return bookRepository.findAll(specification);
    }

    public Boolean delete(Long id) {
        bookRepository.delete(bookRepository.findBook1(id));
        return true;
    }

    public Book getBookByName(String name) {
        return bookRepository.getBookByName(name);
    }

    public Book getBookById(Long id) {
        return bookRepository.findBook1(id);
    }

    public List<Book> getBookByAuthor(String fullName) {
        return bookRepository.findByAuthor(fullName);
    }

    @Transactional

    public Book update(Book book) {
        return bookRepository.save(book);
    }
}
