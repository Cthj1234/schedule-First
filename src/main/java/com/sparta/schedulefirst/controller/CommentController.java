package com.sparta.schedulefirst.controller;

import com.sparta.schedulefirst.dto.CommentRequestDto;
import com.sparta.schedulefirst.dto.CommentResponseDto;
import com.sparta.schedulefirst.entity.Comment;
import com.sparta.schedulefirst.entity.Schedule;
import com.sparta.schedulefirst.repository.CommentRepository;
import com.sparta.schedulefirst.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
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
    public CommentResponseDto create(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 번호의 일정은 존재하지 않습니다.")
        );

        if (Objects.equals(requestDto.getContents(), "")) {
            throw new IllegalArgumentException("댓글 내용이 비어있습니다. 댓글 내용을 입력해주세요.");
        }

        Comment comment = commentRepository.save(new Comment(requestDto, schedule));
        return new CommentResponseDto(comment);
    }
}
