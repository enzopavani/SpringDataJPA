package io.github.enzopavani.libraryapi.controller.mappers;

import io.github.enzopavani.libraryapi.controller.dto.AutorDTO;
import io.github.enzopavani.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "nome", target = "nome")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
