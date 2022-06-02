package com.example.anonymousboard.service;

import com.example.anonymousboard.dto.BoardFormDTO;
import com.example.anonymousboard.dto.MyResponse;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<MyResponse> getAll();

    ResponseEntity<MyResponse> save(BoardFormDTO boardFormDTO);

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> validate(Long id, String password);

}
