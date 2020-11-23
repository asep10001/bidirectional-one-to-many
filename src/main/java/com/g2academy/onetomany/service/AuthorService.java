package com.g2academy.onetomany.service;

import com.g2academy.onetomany.domain.Author;
import com.g2academy.onetomany.domain.Book;
import com.g2academy.onetomany.repository.AuthorRepository;
import com.g2academy.onetomany.repository.BookRepository;
import com.g2academy.onetomany.service.dto.AuthorDto;
import com.g2academy.onetomany.service.dto.BookDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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


    @Transactional
    public Author add(AuthorDto authorDto) {

        Author aEntity = new Author();
        aEntity.setFirstName(authorDto.getFirstName());
        aEntity.setLastName(authorDto.getLastName());

        for (BookDto book : authorDto.getBooks()) {
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

    public Author edit(Long id, AuthorDto authorDto) {
        Optional<Author> oEntity = this.findById(id);
        Author aEntity = oEntity.orElse(null);
        if (aEntity != null) {
            aEntity.setFirstName(authorDto.getFirstName());
            aEntity.setLastName(authorDto.getLastName());
            return repository.save(aEntity);

        }
        return null;
    }

    public void delete(Long id){
       repository.findById(id).map(entity -> {
           repository.delete(entity);
           return ResponseEntity.ok().build();
       }).orElseThrow(()->new NullPointerException("Authour with " + id + " not found")
       );
    }

}
