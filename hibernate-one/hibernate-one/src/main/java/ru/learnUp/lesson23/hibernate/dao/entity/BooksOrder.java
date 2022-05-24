package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BooksOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Client client;

    @Column
    private int orderCost;

    @OneToMany(mappedBy = "booksOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetails> details;
}
