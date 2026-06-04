package com.campusconnect.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private Integer internalMarks;

    @Column(nullable = false)
    private Integer practicalMarks;

    @Column(nullable = false)
    private Integer theoryMarks;

    @Column(nullable = false)
    private Integer totalMarks;

    @Column(nullable = false)
    private String grade;
}
