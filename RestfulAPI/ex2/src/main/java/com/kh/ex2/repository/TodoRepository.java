package com.kh.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.ex2.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>{

}
