package com.rest.todo.dto;

import com.rest.todo.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDTO {
	public String task;
	public boolean isDone;
	public User user;
}
