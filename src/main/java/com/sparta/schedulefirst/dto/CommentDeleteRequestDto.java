package com.sparta.schedulefirst.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDeleteRequestDto {

    // 댓글의 고유 ID (중복 방지)
    private Long id;

    // 사용자 ID
    private String userId = "";

}
