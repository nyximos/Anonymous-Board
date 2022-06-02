package com.example.anonymousboard.service;

import com.example.anonymousboard.domain.Board;
import com.example.anonymousboard.dto.BoardFormDTO;
import com.example.anonymousboard.dto.MyResponse;
import com.example.anonymousboard.dto.StatusEnum;
import com.example.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<MyResponse> save(BoardFormDTO boardFormDTO) {

        Board board = Board.builder()
                .password(boardFormDTO.getPassword())
                .title(boardFormDTO.getTitle())
                .content(boardFormDTO.getContent())
                .createdAt(LocalDateTime.now())
                .views(0)
                .build();

        boardRepository.save(board);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }
}
