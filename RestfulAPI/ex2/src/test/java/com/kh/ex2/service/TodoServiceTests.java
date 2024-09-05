package com.kh.ex2.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.kh.ex2.dto.PageRequestDTO;
import com.kh.ex2.dto.TodoDTO;

@SpringBootTest
public class TodoServiceTests {
	@Autowired
	private TodoService todoService;
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		
		Page<TodoDTO> result = todoService.getList(pageRequestDTO);
		
		System.out.println("PREV: " + result.previousOrFirstPageable());
		System.out.println("NEXT: " + result.nextPageable());
		System.out.println("TOTAL: " + result.getTotalElements());
		
		result.getContent().forEach(todoDTO -> System.out.println(todoDTO));
	}
	
	@Test
	public void testModify() {
		TodoDTO todoDTO = new TodoDTO();
		todoDTO.setMno(109L);
		todoDTO.setTitle("수정함");
		todoDTO.setWriter("수달");
		todoDTO.setDueDate(LocalDate.now());
		
		todoService.modify(todoDTO);
	}
	
	@Test
	public void testDelete() {
		Long mno = 3L;
		
		todoService.remove(mno);
	}
	
	@Test
	public void testRead() {
		Long mno = 120L;
		
		TodoDTO todoDTO = todoService.read(mno);
		System.out.println(todoDTO);
		
	}
	
	@Test
	public void testRegister() {
		TodoDTO todoDTO = new TodoDTO();
		todoDTO.setTitle("Test Todo...");
		todoDTO.setWriter("sudal");
		todoDTO.setDueDate(LocalDate.of(2024, 9, 5));
		
		TodoDTO resultDTO = todoService.register(todoDTO);
		System.out.println(resultDTO);
	}
}
