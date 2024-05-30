package com.sparta.schedulefirst.dto;

import com.sparta.schedulefirst.entity.Comment;
import com.sparta.schedulefirst.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CommentResponseDto {

    private Long id;
    private String commentContents;
    private String userId;
    private Long scheduleId;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.commentContents = comment.getCommentContents();
        this.userId = comment.getUserId();
        this.scheduleId = comment.getSchedule().getId();
        createdAt = comment.getCreatedAt();
    }

}
