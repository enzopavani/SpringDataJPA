package io.github.enzopavani.libraryapi.controller;

import io.github.enzopavani.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.enzopavani.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.enzopavani.libraryapi.controller.mappers.LivroMapper;
import io.github.enzopavani.libraryapi.model.Livro;
import io.github.enzopavani.libraryapi.model.enums.GeneroLivro;
import io.github.enzopavani.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        service.salvar(livro);
        URI location = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable String id) {
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = mapper.toDto(livro);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id) {
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaLivroDTO>> pesquisa(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "titulo", required = false)
            String titulo,
            @RequestParam(value = "nome-autor", required = false)
            String nomeAutor,
            @RequestParam(value = "genero", required = false)
            GeneroLivro genero,
            @RequestParam(value = "ano-publicacao", required = false)
            Integer anoPublicacao
    ){
        var resultado = service.pesquisa(isbn, titulo, nomeAutor, genero, anoPublicacao);
        var lista = resultado.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(lista);
    }
}
