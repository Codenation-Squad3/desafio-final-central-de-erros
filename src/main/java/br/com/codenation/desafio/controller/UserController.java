package br.com.codenation.desafio.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.desafio.dtos.UserDTO;
import br.com.codenation.desafio.exceptions.ObjectCreationException;
import br.com.codenation.desafio.exceptions.ObjectNotFoundException;
import br.com.codenation.desafio.mappers.UserMapper;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserDTO> findAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20")  Integer size,
			@RequestParam(defaultValue = "id")  String atributo,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		
		List<User> pages = userService.findAll(
				PageRequest.of(page, size, direction, atributo)).getContent();
		
		List<UserDTO> dtos = userMapper
				.mapToDTO(pages);
		
		return dtos;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO findById(@PathVariable("id") UUID id) throws ObjectNotFoundException {
		return userService.findById(id)
				.map(userMapper::mapToDTO)
				.orElseThrow(() -> new ObjectNotFoundException("User not found"));
	}	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO save(@RequestBody @Valid UserDTO user) {
		userService.findByEmail(user.getEmail()).ifPresent((v) -> {
			throw new ObjectCreationException("User already registered");
		});
		
		return userMapper.mapToDTO(
				userService.save(
						userMapper.mapToUser(user, UUID.randomUUID())
						));
	}
}
