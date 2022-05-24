package ru.learnUp.lesson23.hibernate.dao.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.filters.StorageFilter;
import ru.learnUp.lesson23.hibernate.dao.repository.BookStorageRepository;
import ru.learnUp.lesson23.hibernate.exceptions.NotEnoughBooksException;

import javax.persistence.LockModeType;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.learnUp.lesson23.hibernate.dao.specifications.StorageSpecification.byFilter;

@Slf4j
@Service
public class BookStorageService {

    private final BookStorageRepository bookStorageRepository;

    public BookStorageService(BookStorageRepository bookStorageRepository) {
        this.bookStorageRepository = bookStorageRepository;
    }

    public BookStorage createBookStorage(BookStorage bookStorage) {
        return bookStorageRepository.save(bookStorage);
    }

    public List<BookStorage> getBookStorage() {
        return bookStorageRepository.findAll();
    }

    public List<BookStorage> getBookStorageBy(StorageFilter filter) {
        Specification<BookStorage> specification = where(byFilter(filter));
        return bookStorageRepository.findAll(specification);
    }

    public BookStorage getBookStorageById(Long id) {
        return bookStorageRepository.getById(id);
    }

    public BookStorage getStorageByBook(Book book) {
        return bookStorageRepository.getByBook(book);
    }

    public Boolean deleteStorage(Long id) {
        bookStorageRepository.delete(getBookStorageById(id));
        return true;
    }

    @Transactional
    @Lock(value = LockModeType.READ)
    public BookStorage update(BookStorage bookStorage) {
        if (bookStorage.getCountOfBooks() >= 0) {
            bookStorageRepository.save(bookStorage);
        } else {
            throw new NotEnoughBooksException("Книг недостаточно на складе");
        }
        return bookStorage;
    }

    @Transactional
    @Lock(value = LockModeType.READ)
    public int buyBook(Book book, int countOfBooks) {
        BookStorage bookStorage = getStorageByBook(book);
        bookStorage.setCountOfBooks(bookStorage.getCountOfBooks() - countOfBooks);
        update(bookStorage);
        return bookStorage.getCountOfBooks();
    }

    @Transactional
    public int addBook(Book book, int countOfBooks) {
        BookStorage bookStorage = getStorageByBook(book);
        bookStorage.setCountOfBooks(bookStorage.getCountOfBooks() + countOfBooks);
        update(bookStorage);
        return bookStorage.getCountOfBooks();
    }
}
