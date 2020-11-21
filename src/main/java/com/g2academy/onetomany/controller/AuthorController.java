package com.g2academy.onetomany.controller;

import com.g2academy.onetomany.domain.Author;
import com.g2academy.onetomany.service.AuthorService;
import com.g2academy.onetomany.service.dto.AuthorDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/authors")
    public List<Author> getAllAuthors() {return authorService.findAll();}

    @PostMapping("/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto){
        Author entity = authorService.add(authorDto);
        return authorDto;
    }
}
