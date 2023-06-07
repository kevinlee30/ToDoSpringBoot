package com.rest.todo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.todo.dto.ToDoDTO;
import com.rest.todo.exception.ResourceNotFoundException;
import com.rest.todo.model.ToDo;
import com.rest.todo.repository.ToDoRepository;
import com.rest.todo.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService{

	  @Autowired
	  private ToDoRepository toDoRepository;
	  
	  @Override
	  public List < ToDoDTO > getAllToDoList() {
		  return toDoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	  }

	  @Override
	  public void save(ToDo toDo) {
		  toDoRepository.save(toDo);
	  }

	  @Override
	  public ToDoDTO findById(Long id) {
	    return convertToDTO(toDoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
	  }

	  @Override
	  public void delete(ToDo toDo) {
		  toDoRepository.delete(toDo);
	  }
	  
	  private ToDoDTO convertToDTO(ToDo toDo) {
	        ToDoDTO toDoDTO = new ToDoDTO();
	        toDoDTO.task = toDo.getTask();
	        toDoDTO.isDone = toDo.isDone();
	        toDoDTO.user = toDo.getUser().clone();
	        return toDoDTO;
	    }
}
