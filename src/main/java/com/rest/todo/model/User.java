package com.rest.todo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column
	private String username;
	
	@OneToMany(mappedBy = "user")
	@Value("#{${listOfStrings}}")
    private List<ToDo> todos;  
	
	public User() {}

	public User(Long id, String username, List<ToDo> todos) {
		super();
		this.userId = id;
		this.username = username;
		this.todos = todos;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ToDo> getTodos() {
		return todos;
	}

	public void setTodos(List<ToDo> todos) {
		this.todos = todos;
	}
	
	public void addTodos(List<ToDo> todos) {
		this.todos.addAll(todos);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public User clone() {
		return new User(getUserId(), getUsername(), getTodos() != null ? getTodos().stream().map(todo -> todo.clone()).toList() : null);
	}
	
	
}
