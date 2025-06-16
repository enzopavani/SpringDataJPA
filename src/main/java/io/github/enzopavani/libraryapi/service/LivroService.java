package io.github.enzopavani.libraryapi.service;

import io.github.enzopavani.libraryapi.model.Livro;
import io.github.enzopavani.libraryapi.model.enums.GeneroLivro;
import io.github.enzopavani.libraryapi.repository.LivroRepository;
import io.github.enzopavani.libraryapi.repository.specs.LivroSpecs;
import io.github.enzopavani.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroValidator validator;

    public Livro salvar(Livro livro) {
        validator.validar(livro);
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn,
            String titulo,
            String nomeAutor,
            GeneroLivro genero,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhoPagina) {
        // select * from livro where 0 = 0
        Specification<Livro> specs = Specification.where(((root, query, cb) -> cb.conjunction()));
        if(isbn != null) {
            // query = query and isbn = :isbn
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }
        if(titulo != null) {
            specs = specs.and(LivroSpecs.tituloLike(titulo));
        }
        if(genero != null) {
            specs = specs.and(LivroSpecs.generoEqual(genero));
        }
        if(anoPublicacao != null) {
            specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));
        }
        if(nomeAutor != null) {
            specs = specs.and(LivroSpecs.nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageRequest);
    }

    public void atualizar(Livro livro) {
        if(livro.getId() == null) {
            throw new IllegalArgumentException("Esse livro não existe na base de dados.");
        }
        validator.validar(livro);
        repository.save(livro);
    }
}
