package io.github.enzopavani.libraryapi.model;

import io.github.enzopavani.libraryapi.model.enums.GeneroLivro;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="livro")
/*
    - Anotação @Data inclui
@Getter@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
 */
@Data
public class Livro {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(name="isbn", length=20, nullable=false)
    private String isbn;

    @Column(name="titulo", length=150, nullable=false)
    private String titulo;

    @Column(name="data_publicação")
    private LocalDate dataPublicação;

    @Enumerated(EnumType.STRING)
    @Column(name="genero", length=30, nullable=false)
    private GeneroLivro genero;

    @Column(name="preco", precision=18, scale=2)
    private Double preco;

    @ManyToOne
    @JoinColumn(name="id_autor")
    private Autor autor;
}
