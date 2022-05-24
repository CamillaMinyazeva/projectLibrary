package ru.learnUp.lesson23.hibernate.dao.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorFilter {

    private final String fullName;
}
