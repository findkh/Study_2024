package com.kh.ex2.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex2.dto.PageRequestDTO;
import com.kh.ex2.dto.TodoDTO;
import com.kh.ex2.entity.TodoEntity;
import com.kh.ex2.exception.EntityNotFoundException;
import com.kh.ex2.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;
	
	public TodoDTO register(TodoDTO todoDTO) {
		TodoEntity todoEntity = todoDTO.toEntity();
		
		todoRepository.save(todoEntity);
		
		return new TodoDTO(todoEntity);
	}
	
	public TodoDTO read(Long mno) {
		Optional<TodoDTO> result = todoRepository.getDTO(mno);
		try {
			TodoDTO todoDTO = result.orElseThrow(() -> new EntityNotFoundException("Todo " + mno + " not Found!"));
			return todoDTO;
		} catch (EntityNotFoundException e) {
			System.out.println("Caught exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public void remove(Long mno) {
		Optional<TodoEntity> result = todoRepository.findById(mno);
		
		try {
			TodoEntity todoEntity = result.orElseThrow(() -> new EntityNotFoundException("Todo " + mno + " not Found!"));
			todoRepository.delete(todoEntity);
		} catch (EntityNotFoundException e) {
			System.out.println("Caught exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public TodoDTO modify(TodoDTO todoDTO) {
		Optional<TodoEntity> result = todoRepository.findById(todoDTO.getMno());
		try {
			TodoEntity todoEntity = result.orElseThrow(() -> new EntityNotFoundException("Todo " + todoDTO.getMno() + " not Found!"));
			todoEntity.changeTitle(todoDTO.getTitle());
			todoEntity.changeWriter(todoDTO.getWriter());
			todoEntity.changeDueDate(todoDTO.getDueDate());
			
			return new TodoDTO(todoEntity);
		} catch (EntityNotFoundException e) {
			System.out.println("Caught exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public Page<TodoDTO> getList(PageRequestDTO pageRequestDTO){
		Sort sort = Sort.by("mno").descending();
		Pageable pageable = pageRequestDTO.getPageable(sort);
		return todoRepository.searchDTO(pageable);
	}

}
