package com.example.anonymousboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id", nullable = false)
    private Long id;

    @Column(name = "board_password", nullable = false)
    private String password;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_content", length = 1000, nullable = false)
    private String content;

    @Column(name = "board_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "views", nullable = false)
    private int views;

}
