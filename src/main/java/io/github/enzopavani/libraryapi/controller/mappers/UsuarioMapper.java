package io.github.enzopavani.libraryapi.controller.mappers;

import io.github.enzopavani.libraryapi.controller.dto.UsuarioDTO;
import io.github.enzopavani.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
