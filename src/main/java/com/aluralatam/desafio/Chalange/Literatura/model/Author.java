package com.aluralatam.desafio.Chalange.Literatura.model;

import com.aluralatam.desafio.Chalange.Literatura.records.DatosAuthor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Books> books = new ArrayList<>();

    public Author(){}
    public Author(DatosAuthor datosAuthor){
        this.name= datosAuthor.name();
        this.birthYear= datosAuthor.birthYear();
        this.deathYear= datosAuthor.deathYear();
    }
    public Long getId() {
        return id;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "Author: " +
                "Name ='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear ;
    }
}
