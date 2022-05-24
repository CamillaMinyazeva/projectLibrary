package ru.learnUp.lesson23.hibernate.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BookViewForStorage {

    private Long id;

    private String name;

    private int countOfSheets;

    private int publishYear;

    private int price;

    private AuthorViewForBook author;
}
