package com.kh.ex2.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex2.dto.TodoDTO;
import com.kh.ex2.entity.TodoEntity;

import lombok.extern.log4j.Log4j2;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Log4j2
public class TodoRepositoryTests2 {
	@Autowired
	private TodoRepository todoRepository;
	
	@Test
	public void testSearchDTO() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		Page<TodoDTO> result = todoRepository.searchDTO(pageable);
		System.out.println(result.getTotalPages());
		System.out.println(result.getTotalElements());
		
		List<TodoDTO> dtoList = result.getContent();
		
		dtoList.forEach(todoDTO -> {
			System.out.println(todoDTO);
		});
	}
	
	@Test
	public void testGetTodoDTO() {
		Long mno = 59L;
		Optional<TodoDTO> result = todoRepository.getDTO(mno);
		
		result.ifPresent(todoDTO -> {
			System.out.println(todoDTO);
		});
	}
	
	@Test
	public void testSearch1() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		Page<TodoEntity> result = todoRepository.search1(pageable);
		System.out.println(result.getTotalPages());
		System.out.println(result.getTotalElements());
		List<TodoEntity> todoEntityList = result.getContent();
		todoEntityList.forEach(todoEntity -> {
			System.out.println(todoEntity);
		});
	}
}
