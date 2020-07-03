package br.com.codenation.desafio.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import br.com.codenation.desafio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.MediaType;

import br.com.codenation.desafio.mappers.UserMapper;
import br.com.codenation.desafio.service.UserService;
import br.com.condenation.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper  userMapper;

	@PostMapping
	public User save(@RequestBody User user){return userService.save(user);}

//	@GetMapping
//	public List<UserDTO> buscarTodos(@RequestParam(defaultValue = "0")   Integer page,
//			@RequestParam(defaultValue = "20")  Integer size,
//			@RequestParam(defaultValue = "id")  String atributo,
//			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
//		return userMapper.mapToDTO(userService.buscarTodos(PageRequest.of(page, size, direction, atributo)).getContent());
//	}
//
//
//	@GetMapping("/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public UserDTO buscarPorId(@PathVariable("id") UUID id) throws Exception {
//		return userService.findById(id)
//				.map(userMapper::mapToUserDTO)
//				.orElseThrow();
//	}

	
	/*
	 * @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) public UserDTO
	 * save(@RequestBody @Valid UserDTO user) { return userMapper.mapToUserDTO(
	 * userService.save( userMapper.mapToUser(user, UUID.randomUUID()) )); }
	 */

	
}
