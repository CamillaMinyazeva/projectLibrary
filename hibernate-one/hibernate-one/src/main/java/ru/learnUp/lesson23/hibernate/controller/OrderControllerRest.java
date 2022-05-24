package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.filters.OrderFilter;
import ru.learnUp.lesson23.hibernate.dao.services.BooksOrderService;
import ru.learnUp.lesson23.hibernate.dao.services.ClientService;
import ru.learnUp.lesson23.hibernate.view.BooksOrderView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("rest/order")
public class OrderControllerRest {

    private final BooksOrderService orderService;
    private final BooksOrderView mapper;
    private final ClientService clientService;

    public OrderControllerRest(BooksOrderService orderService, BooksOrderView mapper, ClientService clientService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.clientService = clientService;
    }

    @GetMapping
    public List<BooksOrderView> getOrders(
            @RequestParam(value = "clientName", required = false) String clientName
    ) {
        return mapper.mapToViewList(orderService.getOrdersBy(new OrderFilter(clientName)));
    }

    @GetMapping("/{orderId}")
    public BooksOrderView getOrder(@PathVariable("orderId") Long orderId) {
        return mapper.mapToView(orderService.getBooksOrderById(orderId));
    }

    @PostMapping
    public BooksOrderView createOrder(@RequestBody BooksOrderView body) {
        if (body.getId() != null) {
            throw new EntityExistsException("Id must be null");
        }
        BooksOrder order = mapper.mapFromView(body, clientService);
        BooksOrder createdOrder = orderService.createBooksOrder(order);
        return mapper.mapToView(createdOrder);
    }

    @PutMapping("/{orderId}")
    @Secured({"ROLE_USER"})
    public BooksOrderView updateOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody BooksOrderView body
    ) {
        if (body.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(orderId, body.getId())) {
            throw new RuntimeException("Entity has bad id");
        }

        BooksOrder order = orderService.getBooksOrderById(orderId);

        if (order.getOrderCost() != body.getOrderCost()) {
            order.setOrderCost(body.getOrderCost());
        }

        BooksOrder updated = orderService.update(order);

        return mapper.mapToView(updated);
    }

    @DeleteMapping("/{orderId}")
    public Boolean deleteOrder(@PathVariable("orderId") Long id) {
        return orderService.delete(id);
    }
}
