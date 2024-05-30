package com.sparta.schedulefirst.controller;

import com.sparta.schedulefirst.dto.ScheduleRequestDto;
import com.sparta.schedulefirst.dto.ScheduleResponseDto;
import com.sparta.schedulefirst.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI를 반환하지 않고 Postman으로 테스트하므로 RestController 사용
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 요구사항1 일정 등록 메소드
    @PostMapping("/schedule/create")
    @Transactional
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 요구사항2 선택 한 일정 조회
    @GetMapping("/schedule/find/{id}")
    public ScheduleResponseDto findSchedule(@PathVariable Long id) {
        return scheduleService.findSchedule(id);
    }

    // 요구사항3 등록된 일정 전체 조회 (단 등록일 순으로 내림차순)
    @GetMapping("/schedule/findAll")
    public List<ScheduleResponseDto> findAll() {
        return scheduleService.findAll();
    }

    // 요구사항4
    //1. 선택한 일정의 `할일 제목`, `할일 내용`, `담당자`을 수정할 수 있습니다.
    //1. 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
    //2. 수정된 일정의 정보를 반환 받아 확인할 수 있습니다.
    @PutMapping("/schedule/update/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.update(id, requestDto);
    }

    //요구사항5 password를 전달받아 삭제
    @DeleteMapping("/schedule/delete/{id}")
    @Transactional
    public void delete(@PathVariable Long id, @RequestBody String password) {
        scheduleService.delete(id, password);
    }
}
