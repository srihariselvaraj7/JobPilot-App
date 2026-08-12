package com.srihari.jobpilot.mapper;

import com.srihari.jobpilot.dto.ResumeResponseDto;
import com.srihari.jobpilot.entity.Resume;
import org.springframework.stereotype.Component;

@Component
public class ResumeMapper {

    public ResumeResponseDto toResponseDto(Resume resume) {

        ResumeResponseDto responseDto = new ResumeResponseDto();

        responseDto.setId(resume.getId());
        responseDto.setFileName(resume.getFileName());
        responseDto.setFileType(resume.getFileType());
        responseDto.setFilePath(resume.getFilePath());
        responseDto.setFileSize(resume.getFileSize());
        responseDto.setUploadedAt(resume.getUploadedAt());

        return responseDto;
    }
}