package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.filters.StorageFilter;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.dao.services.BookStorageService;
import ru.learnUp.lesson23.hibernate.view.BookStorageView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("rest/storage")
public class StorageControllerRest {

    private final BookStorageService bookStorageService;
    private final BookStorageView mapper;
    private final BookService bookService;

    public StorageControllerRest(BookStorageService bookStorageService, BookStorageView mapper,
                                 BookService bookService) {
        this.bookStorageService = bookStorageService;
        this.mapper = mapper;
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookStorageView> getStorage(
            @RequestParam(value = "bookName", required = false) String bookName,
            @RequestParam(value = "address", required = false) String address
    ) {
        return mapper.mapToViewList(bookStorageService.getBookStorageBy(new StorageFilter(bookName, address)));
    }

    @GetMapping("/{storageId}")
    public BookStorageView getStorageById(@PathVariable("storageId") Long storageId) {
        return mapper.mapToView(bookStorageService.getBookStorageById(storageId));
    }

    @PostMapping
    public BookStorageView createStorage(@RequestBody BookStorageView body) {
        if (body.getId() != null) {
            throw new EntityExistsException(
                    String.format("BookStorage with id = %s already exist", body.getId())
            );
        }
        BookStorage storage = mapper.mapFromView(body, bookService);
        BookStorage createdStorage = bookStorageService.update(storage);
        return mapper.mapToView(createdStorage);
    }

    @PutMapping("/{storageId}")
    public BookStorageView updateStorage(
            @PathVariable("storageId") Long storageId,
            @RequestBody BookStorageView body
    ) {
        if (body.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(storageId, body.getId())) {
            throw new RuntimeException("Entity has bad id");
        }

        BookStorage storage = bookStorageService.getBookStorageById(storageId);

        if (storage.getCountOfBooks() != body.getCountOfBooks()) {
            storage.setCountOfBooks(body.getCountOfBooks());
        }

        BookStorage updated = bookStorageService.update(storage);

        return mapper.mapToView(updated);

    }

    @DeleteMapping("/{storageId}")
    public Boolean deleteStorage(@PathVariable("storageId") Long id) {
        return bookStorageService.deleteStorage(id);
    }
}
