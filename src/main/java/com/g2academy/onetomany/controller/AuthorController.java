package com.g2academy.onetomany.controller;

import com.g2academy.onetomany.domain.Author;
import com.g2academy.onetomany.service.AuthorService;
import com.g2academy.onetomany.service.dto.AuthorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @PostMapping("/authors")
    public AuthorDto createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        Author entity = authorService.add(authorDto);
        return authorDto;
    }

    @GetMapping("/authors/{id}")
    public Optional<Author> getOneAuthor(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PutMapping("/authors/{id}")
    public Author editAuthor(@PathVariable Long id, @RequestBody @Valid AuthorDto authorDto) {
        return authorService.edit(id, authorDto);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);

    }
}
