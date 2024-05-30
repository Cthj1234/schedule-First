package com.sparta.schedulefirst.repository;

import com.sparta.schedulefirst.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
