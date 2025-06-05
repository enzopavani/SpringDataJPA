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
import java.util.List;
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
        l1.setIsbn("50356-12845");
        l1.setPreco(BigDecimal.valueOf(240));
        l1.setGenero(GeneroLivro.MISTERIO);
        l1.setTitulo("UFO 2 REMASTERED");
        l1.setDataPublicacao(LocalDate.of(1982, 5, 10));

        Autor autor = autorRepository
                .findById(UUID.fromString("cd7e1905-5817-43c5-a52e-6c4e613ffe46"))
                .orElse(null);
        l1.setAutor(autor);

        repository.save(l1);
    }

    @Test
    void salvarCascadeTest() {
        Livro l1 = new Livro();
        l1.setIsbn("90887-84824");
        l1.setPreco(BigDecimal.valueOf(70));
        l1.setGenero(GeneroLivro.CIENCIA);
        l1.setTitulo("UFO");
        l1.setDataPublicacao(LocalDate.of(1982, 5, 10));

        Autor autor = new Autor();
        autor.setNome("Enzo");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1949, 7, 3));
        l1.setAutor(autor);

        repository.save(l1);
    }

    @Test
    void atualizarAutorDoLivroTest() {
        UUID id = UUID.fromString("605e7608-b474-4405-8f92-efd9a72c0ac1");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("e1cfae53-9ea6-44d5-8429-dec7f32e15b1");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletarTest() {
        UUID id = UUID.fromString("cf2c0ef5-ad36-447b-bfa5-9d536b21d931");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("bab317b5-7a26-4c7d-baa4-bcc094b5f147");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("50356-12845");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPrecoTest() {
        var titulo = "UFO 2 REMASTERED";
        var preco = BigDecimal.valueOf(240);
        List<Livro> lista = repository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloOuIsbnTest() {
        var titulo = "UFO 2 REMASTERED";
        var isbn = "15946-24815";
        List<Livro> lista = repository.findByTituloOrIsbn(titulo, isbn);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivroComQueryJPQL() {
        var resultado = repository.listarTodosOrdenadoPorTituloEPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidos() {
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros() {
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }
}