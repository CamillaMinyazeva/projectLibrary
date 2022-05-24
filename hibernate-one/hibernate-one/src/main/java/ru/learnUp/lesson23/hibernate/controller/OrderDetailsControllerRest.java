package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.dao.services.BooksOrderService;
import ru.learnUp.lesson23.hibernate.dao.services.OrderDetailsService;
import ru.learnUp.lesson23.hibernate.view.OrderDetailsFromView;
import ru.learnUp.lesson23.hibernate.view.OrderDetailsToView;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping("rest/order_details")
public class OrderDetailsControllerRest {

    private final OrderDetailsService detailsService;
    private final OrderDetailsToView mapper;
    private final OrderDetailsFromView mapperFrom;
    private final BookService bookService;
    private final BooksOrderService orderService;

    public OrderDetailsControllerRest(OrderDetailsService detailsService, OrderDetailsToView mapper,
                                      OrderDetailsFromView mapperFrom,
                                      BookService bookService, BooksOrderService orderService) {
        this.detailsService = detailsService;
        this.mapper = mapper;
        this.mapperFrom = mapperFrom;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping("/{order_detailsId}")
    public OrderDetailsToView getDetails(@PathVariable("order_detailsId") Long order_detailsId) {
        return mapper.mapToView(detailsService.getOrderDetailById(order_detailsId));
    }

    @PostMapping
    public OrderDetailsToView createOrderDetail(@RequestBody OrderDetailsFromView body) {
        if (body.getId() != null) {
            throw new EntityExistsException("Id must be null");
        }
        OrderDetails orderDetails = mapperFrom.mapFromView(body, orderService, bookService);
        OrderDetails createdOrderDetails = detailsService.createOrderDetail(orderDetails);
        BooksOrder bookOrder = orderService.getBooksOrderById(
                createdOrderDetails.getBooksOrder().getId()
        );
        bookOrder.setOrderCost(bookOrder.getOrderCost() + createdOrderDetails.getPriceOfBook());
        orderService.update(bookOrder);
        return mapper.mapToView(createdOrderDetails);
    }

    @DeleteMapping("/{order_detailsId}")
    public Boolean deleteOrderDetail(@PathVariable("order_detailsId") Long id) {
        return detailsService.delete(id);
    }
}
