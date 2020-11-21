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


    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    private Author save(Author entity) {
        return repository.save(entity);
    }


    public Author add(AuthorDto authorDto) {

//        Author authorEntity = Author.builder()
//                .firstName(authorDto.getFirstName())
//                .lastName(authorDto.getLastName())
//                .books(new ArrayList<>())
//                .build();

        Author aEntity = new Author();
        aEntity.setFirstName(authorDto.getFirstName());
        aEntity.setLastName(authorDto.getLastName());

        for (Book book : authorDto.getBooks()) {
            Book bookEntity = Book.builder()
                    .author(aEntity)
                    .title(book.getTitle())
                    .publisher(book.getPublisher())
                    .reviews(book.getReviews())
                    .build();
            aEntity.addItem(bookEntity);
        }

        return repository.save(aEntity);


    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }


}
