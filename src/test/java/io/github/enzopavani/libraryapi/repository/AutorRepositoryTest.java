package io.github.enzopavani.libraryapi.repository;

import io.github.enzopavani.libraryapi.model.Autor;
import io.github.enzopavani.libraryapi.model.Livro;
import io.github.enzopavani.libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    void salvarTest() {
        Autor a1 = new Autor();
        a1.setNome("Maria");
        a1.setNacionalidade("Brasileira");
        a1.setDataNascimento(LocalDate.of(1951, 2, 24));

        var autorSalvo = repository.save(a1);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    void atualizarTest() {
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

    @Test
    void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    void deleteByIdTest() {
        var id = UUID.fromString("83404505-ef88-4bab-a4db-05c70c79e558");
        repository.deleteById(id);
    }

    @Test
    void deleteTest() {
        var id = UUID.fromString("167bd199-fbb5-4e3a-9a71-d42bd2ed66a0");
        var marcio = repository.findById(id).get();
        repository.delete(marcio);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("John");
        autor.setNacionalidade("Paraguaia");
        autor.setDataNascimento(LocalDate.of(1970, 11, 10));

        Livro livro = new Livro();
        livro.setIsbn("15946-24815");
        livro.setPreco(BigDecimal.valueOf(110));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("La vida de John");
        livro.setDataPublicacao(LocalDate.of(1998, 6, 24));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("15721-24255");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("La vida fantastica de John");
        livro2.setDataPublicacao(LocalDate.of(1999, 7, 25));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().addAll(Arrays.asList(livro, livro2));

        repository.save(autor);

        // livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutorTest() {
        UUID id = UUID.fromString("40f57d8b-9488-4249-94c4-310ea15ad020");
        var autor = repository.findById(id).get();

        List<Livro> listaLivros = livroRepository.findByAutor(autor);
        autor.setLivros(listaLivros);

        autor.getLivros().forEach(System.out::println);
    }
}
