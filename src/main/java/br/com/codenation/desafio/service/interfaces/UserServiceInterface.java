package br.com.codenation.desafio.service.interfaces;

import java.util.Optional;
import java.util.UUID;

import br.com.codenation.desafio.model.User;

public interface UserServiceInterface extends BaseServiceInterface<User>{

	Optional<User> findById(UUID id);

	Optional<User> findByEmail(String email);

}
