package com.example.boot.controller;

import com.example.boot.entity.Board;
import com.example.boot.repository.BoardRepository;
import com.example.boot.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
public class BoardController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    JwtService jwtService;

    @GetMapping("/api/board/list")
    public ResponseEntity getBoardList(@CookieValue(value = "token", required = false) String token
    ){
        if ( !jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        List<Board> list = boardRepository.findAllByOrderByIdDesc();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/board/detail/{id}")
    public ResponseEntity getBoardDetail(
            @PathVariable("id") Integer id,
            @CookieValue(value = "token", required = false) String token
    ){
        if ( !jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Optional<Board> boardOptional = boardRepository.findById(id);

        Board board = boardOptional.get();
        System.out.println(board.getTitle());
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/api/board/detail")
    public ResponseEntity getBoardUpdate(

    ){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
