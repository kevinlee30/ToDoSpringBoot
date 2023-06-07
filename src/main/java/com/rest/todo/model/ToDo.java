package com.rest.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "todos")
public class ToDo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String task;
	
	@Column(name = "is_done")
	private boolean isDone;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;

	public ToDo() {}

	public ToDo(long id, String task, boolean isDone, User user) {
		super();
		this.id = id;
		this.task = task;
		this.isDone = isDone;
		this.user = user;
	}

	public void toggle() {
		this.isDone = !this.isDone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	public ToDo clone() {
		return new ToDo(getId(), getTask(), isDone(), null);
	}
	
	
}
