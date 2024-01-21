package com.kh.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.mallapi.domain.Todo;

// JpaRepository를 상속해서 만들었기때문에 별도의 메서드를 작성하지 않아도 CRUD와 페이징 처리등 기능이 제공된다.
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
