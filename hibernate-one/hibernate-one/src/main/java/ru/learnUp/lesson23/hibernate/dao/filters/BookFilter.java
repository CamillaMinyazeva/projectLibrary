package ru.learnUp.lesson23.hibernate.dao.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookFilter {

    private final String name;

    private final String publishYear;

    private final String price;

}
