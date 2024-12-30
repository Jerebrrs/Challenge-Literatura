package com.aluralatam.desafio.Chalange.Literatura;

import com.aluralatam.desafio.Chalange.Literatura.principal.Principal;
import com.aluralatam.desafio.Chalange.Literatura.repository.AuthorRepository;
import com.aluralatam.desafio.Chalange.Literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChalangeLiteraturaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChalangeLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(repository,authorRepository);
		principal.mostrarMenu();
	}
}
