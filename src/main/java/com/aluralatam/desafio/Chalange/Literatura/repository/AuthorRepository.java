package com.aluralatam.desafio.Chalange.Literatura.repository;

import com.aluralatam.desafio.Chalange.Literatura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByName(String name);
}
