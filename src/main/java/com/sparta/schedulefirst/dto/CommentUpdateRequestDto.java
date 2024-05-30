package com.sparta.schedulefirst.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateRequestDto {

    // ID (중복 방지, 고유값)
    private Long id;

    // 댓글 내용
    private String contents = "";

    // 사용자 ID
    private String userid = "";


}
