package com.rest.todo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "todo")
public class ToDo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String task;
	
	@Column(name = "is_done")
	private boolean isDone;

	public ToDo() {}
	
	public ToDo(long id, String task, boolean isDone) {
		super();
		this.id = id;
		this.task = task;
		this.isDone = isDone;
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
	
	
}
