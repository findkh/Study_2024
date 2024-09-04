package com.kh.ex2.dto;

import java.time.LocalDate;

import com.kh.ex2.entity.TodoEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDTO {
	private Long mno;
	private String title;
	private String writer;
	private LocalDate dueDate;
	public TodoDTO(TodoEntity todoEntity) {
		this.mno = todoEntity.getMno();
		this.title = todoEntity.getTitle();
		this.writer = todoEntity.getWriter();
		this.dueDate = todoEntity.getDueDate();
	}
}
