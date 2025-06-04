package io.github.enzopavani.libraryapi.repository;

import io.github.enzopavani.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor a1 = new Autor();
        a1.setNome("Maria");
        a1.setNacionalidade("Brasileira");
        a1.setDataNascimento(LocalDate.of(1951, 2, 24));

        var autorSalvo = repository.save(a1);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("167bd199-fbb5-4e3a-9a71-d42bd2ed66a0");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);
        }
    }
}
