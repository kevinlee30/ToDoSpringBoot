package com.rest.todo.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.todo.dto.UserDTO;
import com.rest.todo.exception.ResourceNotFoundException;
import com.rest.todo.model.ToDo;
import com.rest.todo.model.User;
import com.rest.todo.repository.ToDoRepository;
import com.rest.todo.repository.UserRepository;
import com.rest.todo.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ToDoRepository toDoRepository;
	
	// GET all user
	@GetMapping("user")
	public List<UserDTO> getAllUser() {
		List<UserDTO> users = this.userService.getAllUsersList();
		return users;
	}
	
	// GET user by id
	@GetMapping("user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id) {
		UserDTO user = this.userService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	// POST new user
	@PostMapping("user")
	public List<User> createUser(@RequestBody List<User> users) {
		for (User user : users) {
			List<ToDo> todos = user.getTodos();
			User newUser = userRepository.save(user);
			System.out.println("id: " + newUser.getId());
			for(ToDo todo : todos) {
				todo.setUser(newUser);
				toDoRepository.save(todo);
			}
        }
		return users.stream().map(user -> user.clone()).toList();
	}
	
	// DELETE user by id
	@DeleteMapping("user/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUserById(@PathVariable(value = "id") Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	// PUT new username by id
	@PutMapping("user/{id}")
	public ResponseEntity<User> changeUsername(@PathVariable(value = "id") Long id, @RequestBody User userDetail) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		user.setUsername(userDetail.getUsername());
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
}