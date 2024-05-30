package com.sparta.schedulefirst.controller;

import com.sparta.schedulefirst.dto.CommentRequestDto;
import com.sparta.schedulefirst.dto.CommentResponseDto;
import com.sparta.schedulefirst.dto.CommentUpdateRequestDto;
import com.sparta.schedulefirst.entity.Comment;
import com.sparta.schedulefirst.entity.Schedule;
import com.sparta.schedulefirst.repository.CommentRepository;
import com.sparta.schedulefirst.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
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

    // 일정과 댓글을 선택 후 수정한다. (요구사항 3)
    // 일정번호와, 댓글 작성자의 이름(userId)를 받는다
    // 단 한 사람이 여러개의 댓글을 작성했을 수 있기에 고유 식별(comment의 PK)을 위한
    // CommentUpdateRequestDto를 따로 만듬.
    @Transactional
    @PutMapping("/comment/update/{scheduleId}/{userId}")
    public CommentResponseDto update(
            @PathVariable Long scheduleId,
            @PathVariable String userId,
            @RequestBody CommentUpdateRequestDto requestDto) {

        //TODO
        //Optional 적용하기, 공부하기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );

        Comment comment = (Comment)commentRepository.findByUserIdAndId(userId,requestDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글에는 " + userId+ "혹은 commentID : " +requestDto.getUserid() +  " 의 댓글이 없습니다.")
        );

        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }
}
