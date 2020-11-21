package com.g2academy.onetomany.controller;

import com.g2academy.onetomany.domain.Book;
import com.g2academy.onetomany.service.BookService;
import com.g2academy.onetomany.service.ReviewService;
import com.g2academy.onetomany.service.dto.BookDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final BookService service;


    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getHome() {return "Welcome to g2Academy Book";}

    @GetMapping("/books")
    public List<Book> getAllBooks() {return service.findAll();}



    @GetMapping("books/{id}")
    public Optional<Book> getABook(@PathVariable Long id) {return service.findById(id);}

    @PostMapping("/books")
    public BookDto createBook(@RequestBody BookDto bookDto){
        Book entity = service.add(bookDto);
        return bookDto;
    }

    @PutMapping("/books}")
    public BookDto updateBooks(@RequestBody BookDto dto) {

        if (service.edit(dto) != null) {
            return dto;
        } else {
            return null;
        }
    }

}
