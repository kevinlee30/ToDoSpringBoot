package com.rest.todo.service;

import java.util.List;

import com.rest.todo.dto.ToDoDTO;
import com.rest.todo.model.ToDo;

public interface ToDoService {
	public List < ToDoDTO > getAllToDoList();
	public void save(ToDo toDo);
	public ToDoDTO findById(Long id);
	public void delete(ToDo toDo);
}
