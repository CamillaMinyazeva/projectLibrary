package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private BooksOrder booksOrder;

    @OneToOne
    @JoinColumn
    private Book book;

    @Column
    private int countOfBook;

    @Column
    private int priceOfBook;
}
