package com.example.anonymousboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BoardDetailDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int views;

}
