package com.sparta.schedulefirst.repository;

import com.sparta.schedulefirst.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
