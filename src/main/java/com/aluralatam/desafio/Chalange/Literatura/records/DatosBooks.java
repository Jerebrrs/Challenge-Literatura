package com.aluralatam.desafio.Chalange.Literatura.records;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBooks(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count")Integer numeroDeDescargas,
        @JsonAlias("authors") List<DatosAuthor> autor
){

}
