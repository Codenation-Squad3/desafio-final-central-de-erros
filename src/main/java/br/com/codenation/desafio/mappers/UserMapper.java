package br.com.codenation.desafio.mappers;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.codenation.desafio.dtos.UserDTO;
import br.com.codenation.desafio.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "dto.nome", target = "nome")
	@Mapping(source = "dto.email", target = "email")
	@Mapping(source = "dto.password", target = "password")
	User toUser(UserDTO dto, UUID id);
	
	@Mapping(source = "dto.nome", target = "nome")
	@Mapping(source = "dto.email", target = "email")
	@Mapping(source = "dto.createdAt", target = "createdAt")
	@Mapping(source = "dto.password", target = "password")
	UserDTO toUserDTO(User dto);
	
	List<UserDTO> toUserDTO(List<User> users);
	
	List<User> toUser(List<UserDTO> users);
}