package com.kh.mallapi.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.mallapi.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTests {
    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2024, 1, 25))
                .build();

        Long tno = todoService.register(todoDTO);
        log.info("TNO: {}", tno);
    }

    @Test
    public void testGet() {
        Long tno = 101L;

        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO);
    }

    @Test
    public void testModify() {
        Long tno = 101L;

        TodoDTO todoDTO = todoService.get(tno);
        todoDTO.setTitle("제목 수정해봄");
        todoService.modify(todoDTO);
    }

    @Test
    public void testDelete() {
        todoService.remove(101L);
    }
}
