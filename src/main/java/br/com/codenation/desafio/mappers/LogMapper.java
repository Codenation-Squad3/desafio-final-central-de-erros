package br.com.codenation.desafio.mappers;


import br.com.codenation.desafio.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LogMapper {

    LogResourceInput asInput(Log contact);

    void update(@MappingTarget Log contact, LogResourceInput resourceInput);
}