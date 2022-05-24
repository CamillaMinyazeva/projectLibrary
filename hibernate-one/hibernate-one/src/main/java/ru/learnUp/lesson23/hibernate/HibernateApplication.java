package ru.learnUp.lesson23.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.entity.Role;
import ru.learnUp.lesson23.hibernate.dao.entity.User;
import ru.learnUp.lesson23.hibernate.dao.repository.RoleRepository;
import ru.learnUp.lesson23.hibernate.dao.services.*;
import ru.learnUp.lesson23.hibernate.exceptions.NotEnoughBooksException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication()
@EnableCaching
public class HibernateApplication {

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(HibernateApplication.class, args);

//		User user = new User();
//		UserService userService = context.getBean(UserService.class);
//		RoleRepository roleRepository = context.getBean(RoleRepository.class);
//
//		user.setUsername("Camilla");
//		user.setPassword("012345");
//		Set<Role> roles = roleRepository.findAll().stream()
//				.map(role -> roleRepository.findById(1L))
//				.collect(Collectors.toSet());
//		user.setRoles(roles);
//		userService.create(user);

//		BookService bookService = context.getBean(BookService.class);
//		BookStorageService bookStorageService = context.getBean((BookStorageService.class));

		//Check method Searching book by author
//		log.info("Search result: {}", bookService.getBookByAuthor("Erich Maria Remarque"));

		//Check of method but book and add count of books to storage
//		Long id = 2L;
//		log.info("Rest of {} book = {}", id, bookStorageService.buyBook(bookService.getBookById(id), 2));
//		log.info("Rest of {} book = {}", id, bookStorageService.addBook(bookService.getBookById(id), 5));

		//Check locks for update bookStorage
//		updateAsync(bookStorageService, bookService.getBookById(1L));
	}

	static void updateAsync(BookStorageService service, Book book) {

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				try {
					service.buyBook(book, 2);
					log.info("You just bought this book!");
				} catch (NotEnoughBooksException e) {
					log.warn("Sorry, you can't buy this book... try later");
				}
			}).start();
		}
	}
}
