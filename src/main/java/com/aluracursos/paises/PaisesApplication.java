package com.aluracursos.paises;

import com.aluracursos.paises.principal.Principal;
import com.aluracursos.paises.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaisesApplication implements CommandLineRunner {
	@Autowired
	private PaisRepository paisRepository;

	public static void main(String[] args) {
		SpringApplication.run(PaisesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(paisRepository);
		principal.mostrarMenu();
	}
}
