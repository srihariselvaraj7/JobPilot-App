package com.srihari.jobpilot.controller;

import com.srihari.jobpilot.entity.Resume;
import com.srihari.jobpilot.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService){
        this.resumeService=resumeService;
    }

    @PostMapping("/{userId}/resume")
    public ResponseEntity<Resume> uploadResume(
            @PathVariable Integer userId,
            @RequestParam("file") MultipartFile file) throws Exception {

        Resume resume = resumeService.uploadResume(file, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resume);
    }

    @GetMapping("/{userId}/resume")
    public ResponseEntity<Resume> getResumeByUserId(@PathVariable Integer userId) {

        Resume resume = resumeService.getResumeByUserId(userId);
        return ResponseEntity.ok(resume);
    }

    @DeleteMapping("/{userId}/resume")
    public ResponseEntity<String> deleteResume(@PathVariable Integer userId) throws Exception {

        resumeService.deleteResume(userId);
        return ResponseEntity.ok("Resume deleted successfully.");
    }
}
