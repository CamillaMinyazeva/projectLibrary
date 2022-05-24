package ru.learnUp.lesson23.hibernate.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson23.hibernate.dao.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users u where u.username = ?1",
    nativeQuery = true)
    User findByUsername(String userName);

}