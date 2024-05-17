package com.sparta.schedulefirst.dto;

import com.sparta.schedulefirst.entity.Schedule;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ScheduleResponseDto implements Comparable<ScheduleResponseDto>{

    private final int id;
    private final String title;
    private final String contents;
    private final String manager;
    private final ZonedDateTime created_Time;

    // (공통 조건) 비밀번호 반환 X
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.created_Time = schedule.getCreated_Time();
    }


    @Override
    public int compareTo(ScheduleResponseDto o) {
        if (this.created_Time.isBefore(o.created_Time)) {
            return 1;
        } else if (this.created_Time.isAfter(o.created_Time)) {
            return -1;
        }
        return 0;
    }
}
