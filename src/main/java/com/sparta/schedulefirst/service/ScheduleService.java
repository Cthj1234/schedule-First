package com.sparta.schedulefirst.service;

import com.sparta.schedulefirst.dto.ScheduleRequestDto;
import com.sparta.schedulefirst.dto.ScheduleResponseDto;
import com.sparta.schedulefirst.entity.Schedule;
import com.sparta.schedulefirst.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.save(new Schedule(requestDto));
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(id + "번의 고객은 존재하지 않습니다.")
        );
        return new ScheduleResponseDto(schedule);
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> list = scheduleRepository.findAll();

        if (list.isEmpty()) {
            throw new IllegalArgumentException("아무 일정도 등록되지 않았습니다.");
        }
        List<ScheduleResponseDto> responseDtoList = new ArrayList<>();
        for (Schedule schedule : list) {
            responseDtoList.add(new ScheduleResponseDto(schedule));
        }
        return responseDtoList;
    }

    @Transactional
    public void update(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );

        if (!Objects.equals(schedule.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(requestDto);

    }

    @Transactional
    public void delete(Long id, @RequestBody String password) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당되는 일정은 없습니다.")
        );

        if (!Objects.equals(schedule.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
