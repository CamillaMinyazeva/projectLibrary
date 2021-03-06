package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.filters.AuthorFilter;
import ru.learnUp.lesson23.hibernate.dao.repository.AuthorRepository;
import ru.learnUp.lesson23.hibernate.exceptions.NameAlreadyExists;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.learnUp.lesson23.hibernate.dao.specifications.AuthorSpecification.byFilter;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author createAuthor(Author author) {
        if (authorRepository.getAuthorByName(author.getFullName()) != null) {
            throw new NameAlreadyExists("This name already exists");
        }
        return authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.getById(id);
    }

    public List<Author> getAuthorBy(AuthorFilter filter) {
        Specification<Author> specification = where(byFilter(filter));
        return authorRepository.findAll(specification);
    }

    @Transactional
    public Author update(Author author) {
            return authorRepository.save(author);
    }

    public Boolean deleteAuthor(Long id) {
        authorRepository.delete(authorRepository.getById(id));
        return true;
    }
}
