package ru.learnUp.lesson23.hibernate.dao.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorageFilter {

    private final String bookName;

    private final String address;
}
