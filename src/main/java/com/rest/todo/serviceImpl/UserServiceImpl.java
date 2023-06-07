package com.rest.todo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import com.rest.todo.dto.UserDTO;
import com.rest.todo.exception.ResourceNotFoundException;
import com.rest.todo.model.User;
import com.rest.todo.repository.UserRepository;
import com.rest.todo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	  
	  @Autowired
	  private UserRepository userRepository;
	  
	  @Override
	  public List < UserDTO > getAllUsersList() {
		  return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	  }

	  @Override
	  public void save(User user) {
		  userRepository.save(user);
	  }

	  @Override
	  public UserDTO findById(Long id) {
	    return convertToDTO(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
	  }

	  @Override
	  public void delete(User user) {
		  userRepository.delete(user);
	  }
	  
	  private UserDTO convertToDTO(User user) {
	        UserDTO userDTO = new UserDTO();
	        userDTO.username = user.getUsername();
	        userDTO.todos = user.getTodos().stream().map(todo -> todo.clone()).toList();
	        return userDTO;
	    }
}
