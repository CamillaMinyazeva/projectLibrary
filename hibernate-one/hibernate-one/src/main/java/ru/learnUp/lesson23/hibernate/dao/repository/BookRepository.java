package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByName(String name);

    List<Book> findAll(Specification<Book> specification);

    @Query(value = "select * from book b " +
            "where b.name = ?1",
            nativeQuery = true)
    Book findByName(String name);

    @Query(value = "select * from book b " +
            "left join author a on b.author_id = a.id " +
            "where a.full_name = ?1",
            nativeQuery = true)
    List<Book> findByAuthor(String fullName);

    @Query(value = "select * from book b " +
            "where b.id = ?1",
            nativeQuery = true)
    Book findBook1(Long id);

    @Query(value = "select * from book b " +
            "where b.name = ?1",
            nativeQuery = true)
    Book getBookByName(String name);
}
