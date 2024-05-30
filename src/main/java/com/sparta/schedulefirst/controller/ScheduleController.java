package com.sparta.schedulefirst.controller;

import com.sparta.schedulefirst.dto.ScheduleRequestDto;
import com.sparta.schedulefirst.dto.ScheduleResponseDto;
import com.sparta.schedulefirst.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// URI를 반환하지 않고 Postman으로 테스트하므로 RestController 사용
@RestController
@RequestMapping("/api")
public class ScheduleController {

    // Schedule 객체 저장소
    private final HashMap<Integer, Schedule> schedule_List = new HashMap<>();

    // 요구사항1 일정 등록 메소드
    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto);
        int maxId = !schedule_List.isEmpty() ? schedule_List.size() + 1 : 1;
        schedule.setId(maxId);
        schedule_List.put(maxId, schedule);

        return new ScheduleResponseDto(schedule);
    }

    // 요구사항2 선택 한 일정 조회
    @GetMapping("/find/{id}")
    public ScheduleResponseDto findSchedule(@PathVariable int id) {
        if (schedule_List.containsKey(id)) {
            return new ScheduleResponseDto(schedule_List.get(id));
        } else {
            throw new IllegalArgumentException(id + "번의 일정은 저장 되어있지 않습니다.");
        }
    }

    // 요구사항3 등록된 일정 전체 조회 (단 등록일 순으로 내림차순)
    @GetMapping("/findAll")
    public List<ScheduleResponseDto> findAll() {

        if (schedule_List.isEmpty()) {
            throw new IllegalArgumentException("등록된 일정이 없습니다.");
        }
        //내림차순 정렬 (ResponseDto 내부에서 compareTo)
        // Map To List
        List<ScheduleResponseDto> responseList = new ArrayList<>(schedule_List.values().stream()
                .map(ScheduleResponseDto::new).toList());

        Collections.sort(responseList);
        return responseList;
    }

    // 요구사항4
    //1. 선택한 일정의 `할일 제목`, `할일 내용`, `담당자`을 수정할 수 있습니다.
    //1. 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
    //2. 수정된 일정의 정보를 반환 받아 확인할 수 있습니다.
    @PutMapping("/update/{id}")
    public int update(@PathVariable int id, @RequestBody ScheduleRequestDto requestDto) {
        if (!schedule_List.containsKey(id)) {
            throw new IllegalArgumentException("해당 id는 존재하지 않습니다.");
        }

        Schedule schedule = schedule_List.get(id);

        // 비밀번호가 올바르지 않는 경우 예외처리
        if (!Objects.equals(schedule.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 에러 없는 경우 수정
        schedule.update(requestDto);

        schedule_List.put(id, schedule);
        return id;
    }

    //요구사항5 password를 전달받아 삭제
    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int id, @RequestBody String password) {
        if (!schedule_List.containsKey(id)) {
            throw new IllegalArgumentException("해당 id는 존재하지 않습니다.");
        }

        Schedule schedule = schedule_List.get(id);

        // 비밀번호가 올바르지 않는 경우 예외처리
        if (!Objects.equals(schedule.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule_List.remove(id);
        return id;
    }
}
