package com.sparta.schedulefirst.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private String title;
    private String contents;
    private String manager;
    private String password;


    // 등록, 수정 : 할일 제목, 할일 내용, 담당자, 비밀번호, 작성일을 저장할 수 있습니다.
    // 등록, 수정 request
    public ScheduleRequestDto(String title, String contents, String manager, String password) {
        this.title = title;
        this.contents = contents;
        this.manager = manager;
        this.password = password;
    }

    // 삭제시 request
    public ScheduleRequestDto(String password) {
        this.password = password;
    }
}
