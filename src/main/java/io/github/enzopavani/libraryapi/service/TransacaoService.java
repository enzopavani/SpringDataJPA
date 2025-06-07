package io.github.enzopavani.libraryapi.service;

import io.github.enzopavani.libraryapi.model.Autor;
import io.github.enzopavani.libraryapi.model.Livro;
import io.github.enzopavani.libraryapi.model.enums.GeneroLivro;
import io.github.enzopavani.libraryapi.repository.AutorRepository;
import io.github.enzopavani.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar() {

        Autor autor = new Autor();
        autor.setNome("Teste Lúcio");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1949, 7, 3));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("90887-84824");
        livro.setPreco(BigDecimal.valueOf(70));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Teste Livro meio foda");
        livro.setDataPublicacao(LocalDate.of(1968, 8, 18));
        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Lúcio")) {
            throw new RuntimeException("Rollback!");
        }
    }
}
