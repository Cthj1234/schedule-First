package com.sparta.schedulefirst.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    // 댓글 내용
    String contents = "";

    // 사용자 ID
    String userId = "";
}
