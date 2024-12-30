package com.aluralatam.desafio.Chalange.Literatura.repository;

import com.aluralatam.desafio.Chalange.Literatura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Books,Long> {
    Optional<Books> findByTitulo(String titulo);
}
