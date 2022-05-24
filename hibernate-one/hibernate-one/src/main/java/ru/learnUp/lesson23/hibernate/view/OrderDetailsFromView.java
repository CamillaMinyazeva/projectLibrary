package ru.learnUp.lesson23.hibernate.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.dao.services.BooksOrderService;

@Data
@Component
public class OrderDetailsFromView {

    private Long id;

    private BooksOrderView booksOrder;

    private BookViewForDetails book;

    private int countOfBook;

    public OrderDetails mapFromView(OrderDetailsFromView view, BooksOrderService orderService,
                                    BookService bookservice) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setBooksOrder(orderService.getBooksOrderById(view.getBooksOrder().getId()));
        orderDetails.setBook(bookservice.getBookByName(view.getBook().getName()));
        orderDetails.setCountOfBook(view.getCountOfBook());
        orderDetails.setPriceOfBook(bookservice.getBookByName(view.getBook().getName())
                .getPrice() * view.getCountOfBook());
        return orderDetails;
    }
}
