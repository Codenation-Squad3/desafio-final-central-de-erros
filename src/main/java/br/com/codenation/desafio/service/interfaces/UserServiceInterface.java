package br.com.codenation.desafio.service.interfaces;

import br.com.codenation.desafio.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface extends BaseServiceInterface<User> {

    Optional<User> findById(UUID id);

    //Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

}
