package com.g2academy.onetomany.service;


import com.g2academy.onetomany.domain.Book;
import com.g2academy.onetomany.domain.Review;
import com.g2academy.onetomany.repository.BookRepository;
import com.g2academy.onetomany.service.dto.BookDto;
import com.g2academy.onetomany.service.dto.ReviewDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    @PersistenceContext
    private EntityManager em;

    //injection repository

    public BookService(BookRepository repository) {
        this.repository = repository;
    }
    private Book save(Book entity) {return  repository.save(entity); }


    public  Book add(BookDto bookDto){

        Book entity = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .reviews(bookDto.getReviews())
                .build();
        return this.save(entity);
    }

    public List<Book> findAll() {return  repository.findAll();}

    public Optional<Book> findById(Long id) {return repository.findById(id);}

    @Transactional
    public Book edit(BookDto bookDto) {
        Optional<Book> entity = repository.findById(bookDto.getId());

        if(entity.isPresent()){
            Book book = em.find(Book.class, entity.get());

            book.setTitle(book.getTitle());
            book.setAuthor(book.getAuthor());
            book.setPublisher(book.getPublisher());
            book.setReviews(null);
            book.setReviews(book.getReviews());

            return  repository.save(book);
        }else{
            return  null;
        }
    }
}
