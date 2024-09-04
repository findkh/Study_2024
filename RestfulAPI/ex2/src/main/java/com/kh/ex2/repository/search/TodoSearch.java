package com.kh.ex2.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kh.ex2.dto.TodoDTO;
import com.kh.ex2.entity.TodoEntity;

public interface TodoSearch {
	Page<TodoEntity> search1(Pageable pageable);
	Page<TodoDTO> searchDTO(Pageable pageable);
}
