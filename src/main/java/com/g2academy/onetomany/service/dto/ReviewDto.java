package com.g2academy.onetomany.service.dto;


import com.g2academy.onetomany.domain.Book;
import lombok.*;

@Value
@Builder
public class ReviewDto {

    private Long id;
    private String comment;
    private String reviewer;
    private Book book;

}
