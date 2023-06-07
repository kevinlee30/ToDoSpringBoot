package com.rest.todo.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

import com.rest.todo.dto.ToDoDTO;
import com.rest.todo.exception.ResourceNotFoundException;
import com.rest.todo.model.ToDo;
import com.rest.todo.model.User;
import com.rest.todo.repository.ToDoRepository;
import com.rest.todo.repository.UserRepository;
import com.rest.todo.service.ToDoService;

@RestController
@RequestMapping("/")
public class ToDoController {
	@Autowired
	private ToDoRepository toDoRepository;
	@Autowired
	private ToDoService toDoService;
	
	// GET all to do task
	@GetMapping("todo")
	public List<ToDoDTO> getAllToDo() {
		return this.toDoService.getAllToDoList();
	}
	
	// GET to do task by id
	@GetMapping("todo/{id}")
	public ResponseEntity<ToDoDTO> getToDoById(@PathVariable(value = "id") Long id) {
		ToDoDTO toDo = this.toDoService.findById(id);
		return ResponseEntity.ok(toDo);
	}
	
	// POST new to do task
	@PostMapping("todo")
	public List<ToDo> createToDo(@RequestBody List<ToDo> toDos) {
		for (ToDo toDo : toDos) {
			toDoRepository.save(toDo);
		}
		return toDos;
	}
	
	// DELETE to do task by id
	@DeleteMapping("todo/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteToDoById(@PathVariable(value = "id") Long id) {
		ToDo toDo = this.toDoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		toDoRepository.delete(toDo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	// PUT to toggle isDone
	@PutMapping("todo/toggle/{id}")
	public ResponseEntity<ToDo> toggleToDo(@PathVariable(value = "id") Long id) {
		ToDo toDo = this.toDoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		toDo.toggle();
		return ResponseEntity.ok(toDo);
	}
}
