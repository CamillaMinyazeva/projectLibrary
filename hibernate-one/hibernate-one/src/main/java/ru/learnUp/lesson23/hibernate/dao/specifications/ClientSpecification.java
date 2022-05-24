package ru.learnUp.lesson23.hibernate.dao.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.lesson23.hibernate.dao.entity.Client;
import ru.learnUp.lesson23.hibernate.dao.filters.ClientFilter;

import javax.persistence.criteria.Predicate;

public class ClientSpecification {

    public static Specification<Client> byFilter(ClientFilter filter) {

        return (root, q, cb) -> {

            Predicate predicate = cb.isNotNull(root.get("id"));

            if (filter.getFullName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("fullName"), "%" + filter.getFullName() + "%"));
            }
            return predicate;
        };
    }
}
