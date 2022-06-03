package com.example.anonymousboard.service;

import com.example.anonymousboard.domain.Board;
import com.example.anonymousboard.dto.*;
import com.example.anonymousboard.repository.BoardQueryRepository;
import com.example.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    @Override
    public ResponseEntity<MyResponse> getAll() {

        List<Board> boards = boardQueryRepository.findAllDefault();
        List<BoardListViewDTO> boardListViewDTOs = new ArrayList<>();

        for (Board post : boards) {
            BoardListViewDTO boardListViewDTO = BoardListViewDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .createdAt(post.getCreatedAt())
                    .views(post.getViews())
                    .build();

            boardListViewDTOs.add(boardListViewDTO);
        }

        MyResponse<List<BoardListViewDTO>> body = MyResponse.<List<BoardListViewDTO>>builder()
                .header(StatusEnum.OK)
                .body(boardListViewDTOs)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

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

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> get(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        if(board.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.NOT_FOUND)
                    .message("해당 게시글이 없습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        boardEntity.count();

        BoardDetailDTO boardDetailDTO = BoardDetailDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .views(boardEntity.getViews())
                .build();

        MyResponse<BoardDetailDTO> body = MyResponse.<BoardDetailDTO>builder()
                .header(StatusEnum.OK)
                .body(boardDetailDTO)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> validate(Long id, String password) {
        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        if(board.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.NOT_FOUND)
                    .message("해당 게시글이 없습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        if(boardEntity.getPassword().equals(password)) {

            BoardDetailDTO boardDetailDTO = BoardDetailDTO.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .build();

            MyResponse<BoardDetailDTO> body = MyResponse.<BoardDetailDTO>builder()
                    .header(StatusEnum.OK)
                    .body(boardDetailDTO)
                    .message("성공")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);

        } else {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("비밀번호가 일치하지 않습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> update(Long id, BoardUpdateFormDTO boardUpdateFormDTO) {

        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        boardEntity.update(boardUpdateFormDTO);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
