package com.aluralatam.desafio.Chalange.Literatura.principal;

import com.aluralatam.desafio.Chalange.Literatura.model.*;
import com.aluralatam.desafio.Chalange.Literatura.records.DatosResults;
import com.aluralatam.desafio.Chalange.Literatura.repository.AuthorRepository;
import com.aluralatam.desafio.Chalange.Literatura.repository.LibroRepository;
import com.aluralatam.desafio.Chalange.Literatura.service.ConsumoApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final Logger log = LoggerFactory.getLogger(Principal.class);
    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();

    private List<Books> books;
    private  List<Author> authores;
    private LibroRepository repository;
    private AuthorRepository authorRepository;

    public Principal(LibroRepository repository, AuthorRepository authorRepository){
        this.repository=repository;
        this.authorRepository = authorRepository;
    }

    public void mostrarMenu(){
        int opcion = -1;
        while (opcion!=0){
            var menu = """
                    ********--------********
                    1 - Buscar Libro.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en determinados años.
                    5 - Listar libros por Idiomas.
                    0 - Salir.
                    ********--------********
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    getDatosBook();
                    break;
                case 2:
                    getLibrosRegistrados();
                    break;
                case 3:
                    getAthoresRegistrados();
                    break;
                case 4:
                    getAuthoresLive();
                    break;
                case 5:
                    getBooksPorIdiomas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }



    private DatosResults getDatosResults(){
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        DatosResults datos = conversor.obtenerDatos(json,DatosResults.class);
        return datos;
    }

    private void getDatosBook(){
        DatosResults datosResults = getDatosResults();

        List<Books> listaBooks = datosResults.results()
                .stream()
                .map(Books::new)
                .collect(Collectors.toList());

        if (listaBooks.isEmpty()) {
            System.out.println("No se encontraron libros para el título proporcionado.");
        } else {
            Books book = listaBooks.get(0); // Solo intentar acceder si hay al menos un libro

            // Verificación de existencia y guardado del libro
            Optional<Books> existingBook = repository.findByTitulo(book.getTitulo());
            if (existingBook.isPresent()) {
                System.out.println("El libro ya existe en la base de datos: " + existingBook.get());
            } else {
                // Guardar autor y libro
                Author author = book.getAutor();
                Optional<Author> existingAuthor = authorRepository.findByName(author.getName());

                if (existingAuthor.isPresent()) {
                    author = existingAuthor.get(); // Usar el autor existente
                } else {
                    authorRepository.save(author); // Guardar el nuevo autor
                }

                book.setAutor(author); // Asociar el autor al libro
                try {
                    repository.save(book); // Intentar guardar el libro
                    System.out.println("Libro guardado: " + book);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Error: El libro con el título '" + book.getTitulo() + "' ya existe en la base de datos.");
                }
            }
        }
    }

    private void getLibrosRegistrados(){
        books = repository.findAll();

        books.stream().forEach(System.out::println);
    }

    private void getAthoresRegistrados(){
         authores= authorRepository.findAll();

        authores.stream().forEach(System.out::println);
    }

    private void getAuthoresLive(){
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar.");
        var añoLiveAuthor = teclado.nextLine();
        Integer año = Integer.parseInt(añoLiveAuthor);

        authores= authorRepository.findAll();

        List<Author> authoresVivos = authores.stream()
                .filter(author -> {
                    boolean estaVivo= false;

                    if (author.getDeathYear() == null){
                        estaVivo= true;
                    }else{
                        estaVivo = author.getBirthYear() <= año && author.getDeathYear() >= año;
                    }
                    return estaVivo;
                }).collect(Collectors.toList());

        if(authoresVivos.isEmpty()){
            System.out.println("No se encontraron autores vivos en el año: " + añoLiveAuthor);
        }else {
            System.out.println("Authores vivos en el año: " + añoLiveAuthor);
            authoresVivos.forEach(System.out::println);
        }
    }

    public void getBooksPorIdiomas(){
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - Español.
                en - Ingles.
                fr - Frances.
                pt - Portuges.
                """);

        var idiomaElegido = teclado.nextLine();

        books = repository.findAll();

        List<Books> librosPorIdioma = books.stream()
                .filter(book -> book.getIdiomas() != null && book.getIdiomas().contains(idiomaElegido))
                .collect(Collectors.toList());

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idiomaElegido);
        } else {
            System.out.println("Libros en el idioma: " + idiomaElegido);
            librosPorIdioma.forEach(System.out::println);
        }
    }
}
