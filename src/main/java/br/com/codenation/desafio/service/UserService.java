package br.com.codenation.desafio.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.service.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;

@Service("user")
@AllArgsConstructor
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(
				user.getPassword()));
		return this.userRepository.save(user);
	}
	
	@Override
	public Optional<User> findById(UUID id) {
    	return this.userRepository.findById(id);
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
}
