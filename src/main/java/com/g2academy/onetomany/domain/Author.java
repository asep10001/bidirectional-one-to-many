package com.g2academy.onetomany.domain;

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
@Table(name = "table_author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @JsonManagedReference
    @OneToMany(targetEntity = Book.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public void addItem(Book item) {
        this.books.add(item);
        item.setAuthor(this);
    }
}
