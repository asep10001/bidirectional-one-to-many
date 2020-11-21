package com.g2academy.onetomany.repository;

import com.g2academy.onetomany.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long > {
}
