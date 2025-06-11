package io.github.enzopavani.libraryapi.service;

import io.github.enzopavani.libraryapi.model.Livro;
import io.github.enzopavani.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
}
