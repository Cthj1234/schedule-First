package com.sparta.schedulefirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentContents;

    @Column(nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "scheduleId", nullable = false)
    private Schedule schedule;


}