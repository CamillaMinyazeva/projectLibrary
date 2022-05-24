package ru.learnUp.lesson23.hibernate.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

    private Long id;

    private String name;

    private int countOfSheets;

    private int publishYear;

    private int price;

    private AuthorViewForBook author;

    private List<BookStorageViewForBook> storages;

    public BookView mapToView(Book book) {
        BookView view = new BookView();
        view.setId(book.getId());
        view.setName(book.getName());
        view.setPrice(book.getPrice());
        view.setCountOfSheets(book.getCountOfSheets());
        view.setPublishYear(book.getPublishYear());
        view.setAuthor(new AuthorViewForBook(book.getAuthor().getId(), book.getAuthor().getFullName()));
        if (book.getStorages() != null) {
            view.setStorages(
                    book.getStorages().stream()
                            .map(storage -> new BookStorageViewForBook(storage.getId(), storage.getAddress(), storage.getCountOfBooks()))
                            .collect(Collectors.toList())
            );
        }
        return view;
    }

    public List<BookView> mapToViewList(List<Book> books) {
        List<BookView> views = new ArrayList<>();
        for (Book book : books) {
            BookView view = new BookView();
            view.setId(book.getId());
            view.setName(book.getName());
            view.setPrice(book.getPrice());
            view.setCountOfSheets(book.getCountOfSheets());
            view.setPublishYear(book.getPublishYear());
            view.setAuthor(new AuthorViewForBook(book.getAuthor().getId(), book.getAuthor().getFullName()));
            if (book.getStorages() != null) {
                view.setStorages(
                        book.getStorages().stream()
                                .map(storage -> new BookStorageViewForBook(storage.getId(), storage.getAddress(), storage.getCountOfBooks()))
                                .collect(Collectors.toList())
                );
            }
            views.add(view);
        }
        return views;
    }

    public Book mapFromView(BookView view) {
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        book.setId(view.getId());
        book.setName(view.getName());
        book.setPrice(view.getPrice());
        book.setCountOfSheets(view.getCountOfSheets());
        book.setPublishYear(view.getPublishYear());
        books.add(book);
        book.setAuthor(new Author(view.getAuthor().getId(), view.getAuthor().getFullName(), books));
        return book;
    }
}
