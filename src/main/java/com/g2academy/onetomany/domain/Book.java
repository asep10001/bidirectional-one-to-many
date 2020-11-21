package com.g2academy.onetomany.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_book")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column
    private String title;

    @JsonBackReference
    @ManyToOne
    private Author author;

    @Column
    private String publisher;

    @OneToMany( targetEntity = Review.class)
    private List<Review> reviews= new ArrayList<>();

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setBook(this);
    }

}
