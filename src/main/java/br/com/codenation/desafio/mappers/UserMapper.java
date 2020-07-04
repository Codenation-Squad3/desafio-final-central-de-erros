package br.com.codenation.desafio.mappers;


import br.com.codenation.desafio.dtos.response.UserDTO;
import br.com.codenation.desafio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDTO> mapToDTO(List<User> list);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password", ignore = true),
            @Mapping(source = "token", target = "token"),
            @Mapping(source = "logs", target = "logs"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")
    })
    UserDTO mapToUserDTO(User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dto.nome", target = "nome"),
            @Mapping(source = "dto.email", target = "email"),
            @Mapping(source = "dto.password", target = "password", ignore = true),
            @Mapping(source = "dto.token", target = "token"),
            @Mapping(source = "dto.logs", target = "logs"),
            @Mapping(source = "dto.createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")
    })
    User mapToUser(UserDTO dto, UUID id);


}
