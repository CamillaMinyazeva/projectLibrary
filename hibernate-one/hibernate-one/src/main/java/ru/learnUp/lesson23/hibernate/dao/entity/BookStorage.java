package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_storage")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class BookStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn
    private Book book;

    @Column
    private int countOfBooks;
}
