package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll(Specification<Author> specification);

    @Query(value = "select * from author a " +
            "where a.full_name = ?1",
            nativeQuery = true)
    Author getAuthorByName(String name);
}
