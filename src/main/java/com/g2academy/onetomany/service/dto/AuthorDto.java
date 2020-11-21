package com.g2academy.onetomany.service.dto;


import com.g2academy.onetomany.domain.Book;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private long id;
    private String firstName;
    private String lastName;
    private List<Book> books;
}
