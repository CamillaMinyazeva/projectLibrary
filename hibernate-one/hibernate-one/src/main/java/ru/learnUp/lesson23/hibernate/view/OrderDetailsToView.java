package ru.learnUp.lesson23.hibernate.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class OrderDetailsToView {

    private BookViewForDetails book;

    private int countOfBook;

    private int priceOfBook;

    public OrderDetailsToView mapToView(OrderDetails orderDetails) {
        OrderDetailsToView view = new OrderDetailsToView();
        view.setBook(new BookViewForDetails(orderDetails.getBook().getName()));
        view.setCountOfBook(orderDetails.getCountOfBook());
        view.setPriceOfBook(orderDetails.getPriceOfBook());
        return view;
    }

    public List<OrderDetailsToView> mapToView(List<OrderDetails> orderDetailsList) {
        List<OrderDetailsToView> views = new ArrayList<>();
        orderDetailsList.forEach(orderDetails -> {
            OrderDetailsToView view = new OrderDetailsToView();
            view.setBook(new BookViewForDetails(orderDetails.getBook().getName()));
            view.setCountOfBook(orderDetails.getCountOfBook());
            view.setPriceOfBook(orderDetails.getPriceOfBook());
        });
        return views;
    }

}
