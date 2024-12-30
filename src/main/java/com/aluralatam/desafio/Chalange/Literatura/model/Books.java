package com.aluralatam.desafio.Chalange.Literatura.model;


import com.aluralatam.desafio.Chalange.Literatura.records.DatosAuthor;
import com.aluralatam.desafio.Chalange.Literatura.records.DatosBooks;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private List<String> idiomas;
    private Integer numeroDeDescargas;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author autor;

    public Books() {
    }
    public Books(DatosBooks datosBooks){
        this.titulo = datosBooks.titulo();
        this.idiomas= datosBooks.idiomas();
        this.numeroDeDescargas= datosBooks.numeroDeDescargas();

        if (datosBooks.autor() != null && !datosBooks.autor().isEmpty()) {
            DatosAuthor datosAuthor = datosBooks.autor().get(0); // Obtener el primer autor
            this.autor = new Author(datosAuthor); // Crear el autor a partir de DatosAuthor
        } else {
            this.autor = null; // O maneja el caso cuando no haya autor
        }
    }
    @Override
    public String toString() {
        return "Books :"+
                " titulo='" + titulo + '\'' +
                ", idiomas=" + idiomas +'\'' +
                ", numeroDeDescargas=" + numeroDeDescargas +'\'' +
                ", autor=" + autor+'\'' ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }
}
