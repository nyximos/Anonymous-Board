package com.example.anonymousboard.controller;

import com.example.anonymousboard.dto.BoardFormDTO;
import com.example.anonymousboard.dto.MyResponse;
import com.example.anonymousboard.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardServiceImpl boardService;

    @GetMapping("/boards")
    public ResponseEntity<MyResponse> getAll() {
        ResponseEntity<MyResponse> responseEntity = boardService.getAll();
        return responseEntity;
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = boardService.get(id);
        return responseEntity;
    }

    @PostMapping("/boards")
    public ResponseEntity<MyResponse> save(@ModelAttribute BoardFormDTO boardFormDTO) {
        ResponseEntity<MyResponse> responseEntity = boardService.save(boardFormDTO);
        return responseEntity;
    }
}
