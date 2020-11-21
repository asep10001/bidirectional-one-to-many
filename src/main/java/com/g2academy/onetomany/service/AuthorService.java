package com.g2academy.onetomany.service;

import com.g2academy.onetomany.domain.Author;
import com.g2academy.onetomany.domain.Book;
import com.g2academy.onetomany.repository.AuthorRepository;
import com.g2academy.onetomany.repository.BookRepository;
import com.g2academy.onetomany.service.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final BookRepository bookRepository;


    public AuthorService(AuthorRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    private Author save(Author entity) {
        return repository.save(entity);
    }


    public Author add(AuthorDto authorDto) {

        Author authorEntity = Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .build();
        Author entity = this.save(authorEntity);

        List<Book> books = new ArrayList<>();
        for (Book book : authorDto.getBooks()) {
            Book bookEntity = Book.builder()
                    .author(entity)
                    .title(book.getTitle())
                    .publisher(book.getPublisher())
                    .build();

            books.add( bookRepository.save(book));
        }
        entity.setBooks(books);
        return entity;


    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }


}
