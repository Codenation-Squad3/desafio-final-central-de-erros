package br.com.codenation.desafio.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.codenation.desafio.mappers.UserMapper;
import br.com.codenation.desafio.service.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserDTO> buscarTodos(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20")  Integer size,
			@RequestParam(defaultValue = "id")  String atributo,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		return userMapper
				.toUserDTO(userService.buscarTodos(PageRequest.of(page, size, direction, atributo)).getContent());
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO buscarPorId(@PathVariable("id") UUID id) throws Exception {
		return userService.findById(id)
				.map(userMapper::toUserDTO)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO save(@RequestBody @Valid UserDTO user) {
		return userMapper.toUserDTO(
				userService.save(
						userMapper.toUser(user, UUID.randomUUID())
						));
	}	
}