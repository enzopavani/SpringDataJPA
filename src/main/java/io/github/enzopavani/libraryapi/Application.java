package io.github.enzopavani.libraryapi;

import io.github.enzopavani.libraryapi.model.Autor;
import io.github.enzopavani.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository) {
		Autor a1 = new Autor();
		a1.setNome("Márcio");
		a1.setNacionalidade("Brasileiro");
		a1.setDataNascimento(LocalDate.of(1950, 1, 23));

		var autorSalvo = autorRepository.save(a1);
		System.out.println("Autor salvo: " + autorSalvo);
	}
}
