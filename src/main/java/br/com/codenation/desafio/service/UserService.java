package br.com.codenation.desafio.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.service.interfaces.UserServiceInterface;

@Service("user")
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	@Override
	public Optional<User> findById(UUID id) {
    	return this.userRepository.findById(id);
	}

	




}
