package com.sparta.schedulefirst.controller;

import com.sparta.schedulefirst.dto.CommentDeleteRequestDto;
import com.sparta.schedulefirst.dto.CommentRequestDto;
import com.sparta.schedulefirst.dto.CommentResponseDto;
import com.sparta.schedulefirst.dto.CommentUpdateRequestDto;
import com.sparta.schedulefirst.entity.Comment;
import com.sparta.schedulefirst.entity.Schedule;
import com.sparta.schedulefirst.repository.CommentRepository;
import com.sparta.schedulefirst.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;


    // 일정을 선택 후 댓글을 등록한다.
    @PostMapping("/comment/create/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 번호의 일정은 존재하지 않습니다.")
        );

        if (Objects.equals(requestDto.getContents(), "")) {
            throw new IllegalArgumentException("댓글 내용이 비어있습니다. 댓글 내용을 입력해주세요.");
        }

        Comment comment = commentRepository.save(new Comment(requestDto, schedule));
        return new CommentResponseDto(comment);
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

        //TODO
        //Optional 적용하기, 공부하기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );

        //DB에 댓글 존재x
        Comment comment = (Comment) commentRepository.findById(requestDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
        );

        // 사용자 일치X
        if (!Objects.equals(comment.getUserId(), requestDto.getUserId())) {
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        comment.update(requestDto);
        return new CommentResponseDto(comment);
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

        //Schedule 예외처리 부분 Update와 다르게 구현해봤습니다.
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
        }
        ;

        //DB저장 혹은 사용자가 일치하지 않는 경우.
        //DB에 댓글 존재x
        Comment comment = (Comment) commentRepository.findById(requestDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
        );

        System.out.println(comment.getUserId());
        System.out.println("=====================");
        System.out.println("requestDto = " + requestDto.getUserId());
        // 사용자 일치X
        if (!Objects.equals(comment.getUserId(), requestDto.getUserId())) {
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        commentRepository.deleteById(requestDto.getId());

        // 상태코드 변환 요구조건
        return new ResponseEntity<>("댓글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }
}
