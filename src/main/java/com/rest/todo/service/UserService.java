package com.rest.todo.service;

import java.util.List;

import com.rest.todo.dto.UserDTO;
import com.rest.todo.model.User;

public interface UserService {
	public List < UserDTO > getAllUsersList();
	public void save(User user);
	public UserDTO findById(Long id);
	public void delete(User user);
}
