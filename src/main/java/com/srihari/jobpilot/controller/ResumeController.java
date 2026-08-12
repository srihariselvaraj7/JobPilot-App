package com.srihari.jobpilot.controller;

import com.srihari.jobpilot.dto.ResumeResponseDto;
import com.srihari.jobpilot.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping(
            value = "/{userId}/resume",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ResumeResponseDto> uploadResume(
            @PathVariable Integer userId,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        ResumeResponseDto response =
                resumeService.uploadResume(file, userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{userId}/resume")
    public ResponseEntity<ResumeResponseDto> getResumeByUserId(
            @PathVariable Integer userId) {

        ResumeResponseDto response =
                resumeService.getResumeByUserId(userId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/resume")
    public ResponseEntity<Void> deleteResume(
            @PathVariable Integer userId)
            throws IOException {

        resumeService.deleteResume(userId);

        return ResponseEntity.noContent().build();
    }
}