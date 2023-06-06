package com.rest.todo.controller;

import java.util.List;

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

import com.rest.todo.exception.ResourceNotFoundException;
import com.rest.todo.model.ToDo;
import com.rest.todo.repository.ToDoRepository;

@RestController
@RequestMapping("/")
public class ToDoController {
	@Autowired
	private ToDoRepository toDoRepository;
	
	// GET all to do task
	@GetMapping("todo")
	public List<ToDo> getAllToDo() {
		return this.toDoRepository.findAll();
	}
	
	// GET to do task by id
	@GetMapping("todo/{id}")
	public ResponseEntity<ToDo> getToDoById(@PathVariable(value = "id") Long id) {
		ToDo toDo = this.toDoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		return ResponseEntity.ok(toDo);
	}
	
	// POST new to do task
	@PostMapping("todo")
	public ToDo createToDo(@RequestBody ToDo toDo) {
		return this.toDoRepository.save(toDo);
	}
	
	// DELETE to do task by id
	@DeleteMapping("todo/{id}")
	public ResponseEntity<ToDo> deleteToDoById(@PathVariable(value = "id") Long id) {
		ToDo toDo = this.toDoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException());
		toDoRepository.delete(toDo);
		return ResponseEntity.ok(toDo);
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
