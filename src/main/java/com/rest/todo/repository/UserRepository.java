package com.rest.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.todo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
