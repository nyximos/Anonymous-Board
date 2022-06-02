package com.example.anonymousboard.controller;

import com.example.anonymousboard.dto.BoardFormDTO;
import com.example.anonymousboard.dto.MyResponse;
import com.example.anonymousboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<MyResponse> save(@ModelAttribute BoardFormDTO boardFormDTO) {
        ResponseEntity<MyResponse> responseEntity = boardService.save(boardFormDTO);
        return responseEntity;
    }
}
