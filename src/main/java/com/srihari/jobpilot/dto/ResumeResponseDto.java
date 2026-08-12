package com.srihari.jobpilot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponseDto {

        private int id;
        private String fileName;
        private String fileType;
        private String filePath;
        private long fileSize;
        private LocalDateTime uploadedAt;
}