package com.example.anonymousboard.domain;

import com.example.anonymousboard.dto.BoardUpdateFormDTO;
import com.example.anonymousboard.util.StringUtils;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
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
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "views", nullable = false)
    private int views;

    public void count() {
        this.views++;
    }

    public void update(BoardUpdateFormDTO boardUpdateFormDTO) {
        if(StringUtils.isNotBlank(boardUpdateFormDTO.getTitle())) {
            this.title = boardUpdateFormDTO.getTitle();
        }
        if(StringUtils.isNotBlank(boardUpdateFormDTO.getContent())) {
            this.content = boardUpdateFormDTO.getContent();
        }
    }
}
