package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.mappers.UserMapper;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private UserService userService;
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
