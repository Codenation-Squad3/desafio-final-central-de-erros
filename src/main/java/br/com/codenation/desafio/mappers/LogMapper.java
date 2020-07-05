package br.com.codenation.desafio.mappers;


import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LogMapper {

    LogUpdateRequestDTO toDto(Log contact);
    void update(@MappingTarget Log contact, LogUpdateRequestDTO resourceInput);
}
