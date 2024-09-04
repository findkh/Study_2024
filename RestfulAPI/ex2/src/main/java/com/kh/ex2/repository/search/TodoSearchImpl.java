package com.kh.ex2.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.kh.ex2.dto.TodoDTO;
import com.kh.ex2.entity.QTodoEntity;
import com.kh.ex2.entity.TodoEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {
	
	public TodoSearchImpl() {
		super(TodoEntity.class);
	}
	
	@Override
	public Page<TodoEntity> search1(Pageable pageable) {
		log.info("search1!!");
		QTodoEntity todoEntity = QTodoEntity.todoEntity;
		
		JPQLQuery<TodoEntity> query = from(todoEntity);
		
		query.where(todoEntity.mno.gt(0L));
		
		getQuerydsl().applyPagination(pageable, query);
		
		List<TodoEntity> entityList = query.fetch();
		
		long count = query.fetchCount();
		return new PageImpl<>(entityList, pageable, count);
	}
	
	@Override
	public Page<TodoDTO> searchDTO(Pageable pageable){
		QTodoEntity todoEntity = QTodoEntity.todoEntity;
		JPQLQuery<TodoEntity> query = from(todoEntity);
		query.where(todoEntity.mno.gt(0L));
		getQuerydsl().applyPagination(pageable, query);
		
		JPQLQuery<TodoDTO> dtoQuery = query.select(Projections.constructor(TodoDTO.class, todoEntity));
		List<TodoDTO> dtoList = dtoQuery.fetch();
		long count = dtoQuery.fetchCount();
		return new PageImpl<>(dtoList, pageable, count);
	}
}
