package io.github.enzopavani.libraryapi.controller;

import io.github.enzopavani.libraryapi.controller.dto.UsuarioDTO;
import io.github.enzopavani.libraryapi.controller.mappers.UsuarioMapper;
import io.github.enzopavani.libraryapi.model.Usuario;
import io.github.enzopavani.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }
}
