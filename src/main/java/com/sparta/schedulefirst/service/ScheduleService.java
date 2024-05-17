package com.sparta.schedulefirst.service;

import com.sparta.schedulefirst.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


// 나중에 할 곳
@Service
public class ScheduleService {

    private final Map<Integer, Schedule> scheduleList = new HashMap<>();
}
