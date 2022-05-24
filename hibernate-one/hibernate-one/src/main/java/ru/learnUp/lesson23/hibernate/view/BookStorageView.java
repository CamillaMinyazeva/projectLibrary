package ru.learnUp.lesson23.hibernate.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BookStorageView {

    private Long id;

    private String address;

    private BookViewForStorage book;

    private int countOfBooks;

    public BookStorageView mapToView(BookStorage bookStorage) {
        BookStorageView view = new BookStorageView();
        view.setId(bookStorage.getId());
        view.setAddress(bookStorage.getAddress());
        view.setBook(new BookViewForStorage(bookStorage.getBook().getId(), bookStorage.getBook().getName(),
                bookStorage.getBook().getCountOfSheets(), bookStorage.getBook().getPublishYear(),
                bookStorage.getBook().getPrice(),
                new AuthorViewForBook(bookStorage.getBook().getAuthor().getId(),
                        bookStorage.getBook().getAuthor().getFullName())));
        view.setCountOfBooks(bookStorage.getCountOfBooks());
        return view;
    }

    public List<BookStorageView> mapToViewList(List<BookStorage> bookStorages) {
        List<BookStorageView> views = new ArrayList<>();
        for (BookStorage bookStorage: bookStorages) {
            BookStorageView view = new BookStorageView();
            view.setId(bookStorage.getId());
            view.setAddress(bookStorage.getAddress());
            view.setBook(new BookViewForStorage(bookStorage.getBook().getId(), bookStorage.getBook().getName(),
                    bookStorage.getBook().getCountOfSheets(), bookStorage.getBook().getPublishYear(),
                    bookStorage.getBook().getPrice(),
                    new AuthorViewForBook(bookStorage.getBook().getAuthor().getId(),
                            bookStorage.getBook().getAuthor().getFullName())));
            view.setCountOfBooks(bookStorage.getCountOfBooks());
            views.add(view);
        }
        return views;
    }

    public BookStorage mapFromView(BookStorageView view, BookService bookService) {
        BookStorage bookStorage = new BookStorage();
        bookStorage.setId(view.getId());
        bookStorage.setAddress(view.getAddress());
        bookStorage.setBook(bookService.getBookByName(view.getBook().getName()));
        bookStorage.setCountOfBooks(view.getCountOfBooks());
        return bookStorage;
    }
}
