package io.github.enzopavani.libraryapi.repository.specs;

import io.github.enzopavani.libraryapi.model.Livro;
import io.github.enzopavani.libraryapi.model.enums.GeneroLivro;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    // where isbn = :isbn
    public static Specification<Livro> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    // upper(livro.titulo) like (%:param%)
    public static Specification<Livro> tituloLike(String titulo) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(GeneroLivro genero) {
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);

    }

    // to_char(data_publicacao, 'YYYY') = :anoPublicacao
    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao) {
        return (root, query, cb) ->
                cb.equal(cb.function("to_char", String.class,
                        root.get("dataPublicacao"), cb.literal("YYYY")), anoPublicacao.toString());
    }
}