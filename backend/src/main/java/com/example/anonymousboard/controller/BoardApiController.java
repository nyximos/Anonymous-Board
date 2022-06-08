package com.example.anonymousboard.controller;

import com.example.anonymousboard.dto.BoardFormDTO;
import com.example.anonymousboard.dto.BoardUpdateFormDTO;
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

    @GetMapping("/boards/views")
    public ResponseEntity<MyResponse> getAllByViews() {
        ResponseEntity<MyResponse> responseEntity = boardService.getAllByViews();
        return responseEntity;
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = boardService.get(id);
        return responseEntity;
    }

    @GetMapping("/boards/{id}/update")
    public ResponseEntity<MyResponse> validate(@PathVariable("id") Long id, @RequestParam("password") String password) {
        ResponseEntity<MyResponse> responseEntity = boardService.validate(id, password);
        return responseEntity;
    }

    @PostMapping("/boards")
    public ResponseEntity<MyResponse> save(@ModelAttribute BoardFormDTO boardFormDTO) {
        ResponseEntity<MyResponse> responseEntity = boardService.save(boardFormDTO);
        return responseEntity;
    }

    @PatchMapping("/boards/{id}")
    public ResponseEntity<MyResponse> update(@PathVariable("id") Long id, BoardUpdateFormDTO boardUpdateFormDTO) {
        ResponseEntity<MyResponse> responseEntity = boardService.update(id, boardUpdateFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<MyResponse> remove(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = boardService.remove(id);
        return responseEntity;
    }
}
