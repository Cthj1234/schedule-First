package com.sparta.schedulefirst.controller;

import com.sparta.schedulefirst.dto.ScheduleRequestDto;
import com.sparta.schedulefirst.dto.ScheduleResponseDto;
import com.sparta.schedulefirst.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

// URI를 반환하지 않고 Postman으로 테스트하므로 RestController 사용
@RestController
public class ScheduleController {

    // Schedule 객체 저장소
    private final HashMap<Integer, Schedule> schedule_List = new HashMap<>();

    // 요구사항1 일정 등록 메소드
    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto);
        int maxId = !schedule_List.isEmpty() ? schedule_List.size() + 1 : 1;
        schedule.setId(maxId);
        schedule_List.put(maxId,schedule);

        return new ScheduleResponseDto(schedule);
    }
    
    // 요구사항2 선택 한 일정 조회
    @GetMapping("/find/{id}")
    public ScheduleResponseDto findSchedule(@PathVariable int id){
        if (schedule_List.containsKey(id)) {
            return new ScheduleResponseDto(schedule_List.get(id));
        }else{
            throw new IllegalArgumentException(id + "번의 일정은 저장 되어있지 않습니다.");
        }
    }
    
    // 요구사항3 등록된 일정 전체 조회 (단 등록일 순으로 내림차순)
    @GetMapping("/findAll")
    public List<ScheduleResponseDto> findAll(){

        //내림차순 정렬 (ResponseDto 내부에서 compareTo)
        // Map To List
        List<ScheduleResponseDto> responseList = new ArrayList<>(schedule_List.values().stream()
                .map(ScheduleResponseDto::new).toList());

        Collections.sort(responseList);
        return responseList;
    }



}
