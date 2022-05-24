package ru.learnUp.lesson23.hibernate.dao.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientFilter {

    private final String fullName;
}
