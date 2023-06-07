package com.rest.todo.dto;

import java.util.List;

import com.rest.todo.model.ToDo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	public String username;
	public List<ToDo> todos;
	
}
