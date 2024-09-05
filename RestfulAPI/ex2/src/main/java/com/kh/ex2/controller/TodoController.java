package com.kh.ex2.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex2.dto.PageRequestDTO;
import com.kh.ex2.dto.TodoDTO;
import com.kh.ex2.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/todos")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
	private final TodoService todoService;
	
	@GetMapping("/list")
	public ResponseEntity<Page<TodoDTO>> list(@Validated PageRequestDTO pageRequestDTO){
		log.info("list...");
		return ResponseEntity.ok(todoService.getList(pageRequestDTO));
	}
	
	@DeleteMapping("/{mno}")
	public ResponseEntity<Map<String, String>> remove(@PathVariable("mno") Long mno){
		log.info("remove...");
		log.info(mno);
		
		todoService.remove(mno);
		
		Map<String, String> result = Map.of("result", "success");
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{mno}")
	public ResponseEntity<TodoDTO> modify(@PathVariable("mno") Long mno, @RequestBody TodoDTO todoDTO) {
		log.info("modify...");
		log.info(mno);
		log.info(todoDTO);
		
		todoDTO.setMno(mno);
		
		TodoDTO modifiedTodoDTO = todoService.modify(todoDTO);
		return ResponseEntity.ok(modifiedTodoDTO);
	}
	
	@GetMapping("/{mno}")
	public ResponseEntity<TodoDTO> read(@PathVariable("mno") Long mno){
		log.info("read...");
		log.info(mno);
		return ResponseEntity.ok(todoService.read(mno));
	}
	
	@PostMapping("")
	public ResponseEntity<TodoDTO> register(@RequestBody @Validated TodoDTO todoDTO){
		log.info("regiseter 호출...");
		log.info(todoDTO);
		
		return ResponseEntity.ok(todoService.register(todoDTO));
	}
	
	//BindingResult 방법...
//	@PostMapping("")
//	public ResponseEntity<TodoDTO> register(@RequestBody @Validated TodoDTO todoDTO, BindingResult bindingResult){
//		log.info("regiseter 호출...");
//		log.info(todoDTO);
//		
//		if(bindingResult.hasErrors()) {
//			log.info(bindingResult.getAllErrors());
//			return ResponseEntity.badRequest().build();
//		}
//		
//		return null;
//	}
}
