package com.g2academy.onetomany.service.dto;

import com.g2academy.onetomany.domain.Author;
import com.g2academy.onetomany.domain.Review;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookDto {

    private long id;
    private Author author;
    private String publisher;
    private String title;
    private List<Review> reviews;

}
