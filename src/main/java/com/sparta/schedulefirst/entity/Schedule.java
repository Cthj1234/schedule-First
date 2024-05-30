package com.sparta.schedulefirst.entity;

import com.sparta.schedulefirst.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String manager;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private ZonedDateTime created_Time;


    // new Constructor 생성
    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.created_Time = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    // 할일 제목, 할일 내용, 담당자, 변경시간 수정
    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.created_Time = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
