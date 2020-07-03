package br.com.codenation.desafio.mappers;


import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.codenation.desafio.model.User;
import br.com.condenation.dtos.UserDTO;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
//	 @Mappings({
//	  @Mapping(source = "dto.id", target = "id"),
//	  @Mapping(source = "dto.nome", target = "nome"),
//	  @Mapping(source = "dto.email", target = "email"),
//	  @Mapping(source = "dto.password", target = "password", ignore = true),
//	  @Mapping(source = "dto.token", target = "token"),
//	  @Mapping(source = "dto.logs", target = "logs"),
//	  @Mapping(source = "dto.createdAt", target = "createdAt", dateFormat ="yyyy-MM-dd HH:mm"), })
//
//	  List<UserDTO> mapToDTO(List<User> list);
	  
	  UserDTO mapToUserDTO(User user);
	  
	  User mapToUser(UserDTO userDTO, UUID id);
	 
  
}
