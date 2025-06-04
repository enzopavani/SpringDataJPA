package io.github.enzopavani.libraryapi.repository;

import io.github.enzopavani.libraryapi.model.Autor;
import io.github.enzopavani.libraryapi.model.Livro;
import io.github.enzopavani.libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro l1 = new Livro();
        l1.setIsbn("90887-84824");
        l1.setPreco(BigDecimal.valueOf(70));
        l1.setGenero(GeneroLivro.CIENCIA);
        l1.setTitulo("UFO");
        l1.setDataPublicação(LocalDate.of(1982, 5, 10));

        Autor autor = autorRepository
                .findById(UUID.fromString("cd7e1905-5817-43c5-a52e-6c4e613ffe46"))
                .orElse(null);
        l1.setAutor(autor);

        repository.save(l1);
    }
}