package com.srihari.jobpilot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="resume")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false, unique = true)
    private String filePath;

    @Column(nullable = false)
    private long fileSize;

    @CreationTimestamp
    private LocalDateTime uploadedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable = false, unique = true)
    private User user;
}
