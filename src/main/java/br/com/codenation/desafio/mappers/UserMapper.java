package br.com.codenation.desafio.mappers;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.codenation.desafio.dtos.UserDTO;
import br.com.codenation.desafio.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "nome", target = "nome"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "password", target = "password", ignore = true),
		@Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
	})
	UserDTO mapToDTO(User user);
	
	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "dto.nome", target = "nome"),
		@Mapping(source = "dto.email", target = "email"),
		@Mapping(source = "dto.password", target = "password"),
		@Mapping(source = "dto.createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
	})
	User mapToUser(UserDTO dto, UUID id);
	
	List<UserDTO> mapToDTO(List<User> list);
	
	List<User> mapToUser(List<UserDTO> users);
}