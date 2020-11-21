package com.g2academy.onetomany.service;

import com.g2academy.onetomany.domain.Book;
import com.g2academy.onetomany.domain.Review;
import com.g2academy.onetomany.repository.ReviewRepository;
import com.g2academy.onetomany.service.dto.ReviewDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private  final ReviewRepository repository;
    @PersistenceContext
    private EntityManager em;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    private Review save(Review entity) {return repository.save(entity);}

    public Review add (ReviewDto reviewDto){
        Review entity = new Review();
                entity.setComments(reviewDto.getComment());
                entity.setReviewer(reviewDto.getReviewer());

        List<Book> books = new ArrayList<>();
        for (Book book:  books) {
            if(book.getId() ==  reviewDto.getBook().getId()){
                Book bookEntity = new Book();
                bookEntity.addReview(entity);
                entity.setBook(book);
            }
        }
        return this.save(entity);
    }

    public List<Review> findAll() { return repository.findAll();}

    public Optional<Review> findById(Long id) {return repository.findById(id);}

    public Review edit(ReviewDto reviewDto){
        Optional<Review> entity = repository.findById(reviewDto.getId());

        if(entity.isPresent()){
            Review review = entity.get();
            Book b = em.find(Book.class, entity.get().getBook());
            review.setComments(reviewDto.getComment());
            review.setReviewer(reviewDto.getReviewer());
            review.setBook(b);

            b.getReviews().add(review);

            em.persist(review);

            return repository.save(review);
        }else{
            return  null;
        }
    }
}
