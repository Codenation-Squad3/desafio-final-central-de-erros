package br.com.codenation.desafio.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.service.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	@Override
	public Optional<User> findById(UUID id) {
    	return this.userRepository.findById(id);
	}
	
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

	@Override
	public Optional<User> findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
}
