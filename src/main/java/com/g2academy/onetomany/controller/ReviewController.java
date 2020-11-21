package com.g2academy.onetomany.controller;

import com.g2academy.onetomany.domain.Author;
import com.g2academy.onetomany.domain.Book;
import com.g2academy.onetomany.domain.Review;
import com.g2academy.onetomany.service.ReviewService;
import com.g2academy.onetomany.service.dto.AuthorDto;
import com.g2academy.onetomany.service.dto.BookDto;
import com.g2academy.onetomany.service.dto.ReviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {return reviewService.findAll();}

    @PostMapping("/reviews")
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto){
        Review entity = reviewService.add(reviewDto);
        return reviewDto;
    }
}
