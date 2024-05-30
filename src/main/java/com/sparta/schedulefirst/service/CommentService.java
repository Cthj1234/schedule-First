package com.sparta.schedulefirst.service;

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
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;


    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 번호의 일정은 존재하지 않습니다.")
        );

        if (Objects.equals(requestDto.getContents(), "")) {
            throw new IllegalArgumentException("댓글 내용이 비어있습니다. 댓글 내용을 입력해주세요.");
        }

        Comment comment = commentRepository.save(new Comment(requestDto, schedule));
        return new CommentResponseDto(comment);
    }

    public CommentResponseDto updateComment(Long scheduleId, CommentUpdateRequestDto requestDto) {

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


    public ResponseEntity<String> deleteComment(Long scheduleId, CommentDeleteRequestDto requestDto) {
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
