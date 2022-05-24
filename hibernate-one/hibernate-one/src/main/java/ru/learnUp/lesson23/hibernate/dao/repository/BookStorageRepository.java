package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;

import java.util.List;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorage, Long> {

    List<BookStorage> findAll(Specification<BookStorage> specification);

    @Query(value = "select * from book_storage bs " +
            "where bs.book_id = ?1 for update",
            nativeQuery = true)
    BookStorage getByBook(Book book);
}
