package com.sparta.schedulefirst.controller;

import com.sparta.schedulefirst.dto.CommentDeleteRequestDto;
import com.sparta.schedulefirst.dto.CommentRequestDto;
import com.sparta.schedulefirst.dto.CommentResponseDto;
import com.sparta.schedulefirst.dto.CommentUpdateRequestDto;
import com.sparta.schedulefirst.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 일정을 선택 후 댓글을 등록한다.
    @PostMapping("/comment/create/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(id, requestDto);
    }


    // 일정과 댓글을 선택 후 수정한다. (요구사항 3)
    // 일정번호와, 댓글 작성자의 이름(userId)를 받는다
    // 단 한 사람이 여러개의 댓글을 작성했을 수 있기에 고유 식별(comment의 PK)을 위한
    // CommentUpdateRequestDto를 따로 만듬.
    @Transactional
    @PutMapping("/comment/update/{scheduleId}")
    public CommentResponseDto updateComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentUpdateRequestDto requestDto) {

        return commentService.updateComment(scheduleId, requestDto);
    }

    // 기능
    //- 선택한 일정의 댓글을 삭제합니다.
    // 조건
    //- 성공했다는 메시지와 상태 코드 반환하기
    //- 선택한 일정과 댓글이 DB에 저장되어 있어야 합니다.
    // 댓글 내용이 필요 없기에 DeleteRequestDto 추가
    @Transactional
    @DeleteMapping("/comment/delete/{scheduleId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long scheduleId, @RequestBody CommentDeleteRequestDto requestDto) {
        return commentService.deleteComment(scheduleId, requestDto);
    }
}
