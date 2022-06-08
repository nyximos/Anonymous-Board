package com.example.anonymousboard.repository;

import com.example.anonymousboard.domain.Board;
import com.example.anonymousboard.domain.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.anonymousboard.domain.QBoard.*;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Board> findAllDefault() {
        return queryFactory
                .selectFrom(board)
                .orderBy(board.createdAt.desc())
                .fetch();
    }

    public List<Board> findAllByViews() {
        return queryFactory
                .selectFrom(board)
                .orderBy(board.views.desc())
                .fetch();
    }
}
