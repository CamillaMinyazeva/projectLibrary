package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.services.*;
import ru.learnUp.lesson23.hibernate.exceptions.NotEnoughBooksException;
import ru.learnUp.lesson23.hibernate.view.BookViewForPurchase;
import ru.learnUp.lesson23.hibernate.view.PurchaseFromView;

@RestController
@RequestMapping("/purchase")
public class PurchaseControllerRest {

    private final ClientService clientService;
    private final BooksOrderService orderService;
    private final OrderDetailsService detailsService;
    private final BookService bookService;
    private final BookStorageService storageService;

    public PurchaseControllerRest(ClientService clientService,
                                  BooksOrderService orderService,
                                  OrderDetailsService detailsService,
                                  BookService bookService, BookStorageService storageService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.detailsService = detailsService;
        this.bookService = bookService;
        this.storageService = storageService;
    }

    @PostMapping
    public String createPurchase(@RequestBody PurchaseFromView purchaseView) {

        for (BookViewForPurchase bookFromView : purchaseView.getBooks()) {
            if (storageService.getStorageByBook(
                    bookService.
                            getBookByName(bookFromView.getBookName()))
                    .getCountOfBooks() < bookFromView.getCountOfBook()) {
                return "Not enough books: \"" + bookFromView.getBookName() + "\"\n"
                        + "We just "
                        + storageService.getStorageByBook(bookService.
                                getBookByName(bookFromView.getBookName()))
                        .getCountOfBooks()
                        + " book/s...";
            }
        }

        StringBuilder result = new StringBuilder("");
        BooksOrder order = new BooksOrder();
        order.setClient(clientService.getClientById(purchaseView.getClientId()));
        BooksOrder createdOrder = orderService.createBooksOrder(order);

        for (BookViewForPurchase bookFromView : purchaseView.getBooks()) {
            OrderDetails detail = new OrderDetails();
            Book book = bookService.getBookByName(bookFromView.getBookName());
            storageService.buyBook(book, bookFromView.getCountOfBook());
            result.append("You buy \"" + bookFromView.getBookName() + "\" effectively.\n");
            detail.setBook(book);
            detail.setBooksOrder(createdOrder);
            detail.setCountOfBook(bookFromView.getCountOfBook());
            detail.setPriceOfBook(book.getPrice() * detail.getCountOfBook());
            detailsService.createOrderDetail(detail);
            createdOrder.setOrderCost(createdOrder.getOrderCost() + detail.getPriceOfBook());
            orderService.update(createdOrder);
        }

        return result.toString() + "Thank you!";
    }
}
