package com.example.boot.controller;

import com.example.boot.entity.Board;
import com.example.boot.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BoardController {
    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/api/board/list")
    public ResponseEntity getBoardList(){

        List<Board> list = boardRepository.findAllByOrderByIdDesc();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
