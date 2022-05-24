package ru.learnUp.lesson23.hibernate.dao.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.filters.StorageFilter;

import javax.persistence.criteria.Predicate;

public class StorageSpecification {

    public static Specification<BookStorage> byFilter(StorageFilter filter) {

        return (root, q, cb) -> {

            Predicate predicate = cb.isNotNull(root.get("id"));

            if (filter.getBookName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("book").get("name"), "%" + filter.getBookName() + "%"));
            }

            if (filter.getAddress() != null) {
                predicate = cb.and(predicate, cb.like(root.get("address"), "%" + filter.getAddress() + "%"));
            }

            return predicate;
        };
    }

}
