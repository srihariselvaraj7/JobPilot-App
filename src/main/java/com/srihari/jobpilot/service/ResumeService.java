package com.srihari.jobpilot.service;

import com.srihari.jobpilot.dto.ResumeResponseDto;
import com.srihari.jobpilot.entity.Resume;
import com.srihari.jobpilot.entity.User;
import com.srihari.jobpilot.exception.ResumeNotFoundException;
import com.srihari.jobpilot.exception.UserNotFoundException;
import com.srihari.jobpilot.mapper.ResumeMapper;
import com.srihari.jobpilot.repository.ResumeRepository;
import com.srihari.jobpilot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final ResumeMapper resumeMapper;

    @Autowired
    public ResumeService(
            ResumeRepository resumeRepository,
            UserRepository userRepository,
            ResumeMapper resumeMapper) {

        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
        this.resumeMapper = resumeMapper;
    }

    public ResumeResponseDto uploadResume(
            MultipartFile file,
            Integer userId) throws IOException {

        // 1. Check whether user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with Id " + userId));

        // 2. Check whether resume already exists
        Optional<Resume> existingResume =
                resumeRepository.findByUserId(userId);

        Resume resume;

        if (existingResume.isPresent()) {

            // Update existing resume
            resume = existingResume.get();

            // Delete old file
            Files.deleteIfExists(
                    Paths.get(resume.getFilePath()));

        } else {

            // Create new resume
            resume = new Resume();
            resume.setUser(user);
        }

        // 3. Create uploads directory if it doesn't exist
        String uploadDir = "uploads/resumes/";
        Files.createDirectories(Paths.get(uploadDir));

        // 4. Generate unique filename
        String fileName =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        // 5. Save file to disk
        Path path = Paths.get(uploadDir + fileName);

        Files.copy(
                file.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING
        );

        // 6. Update Resume entity
        resume.setFileName(fileName);
        resume.setFileType(file.getContentType());
        resume.setFilePath(path.toString());
        resume.setFileSize(file.getSize());

        // 7. Save in database
        Resume savedResume =
                resumeRepository.save(resume);

        // 8. Convert Entity → Response DTO
        return resumeMapper.toResponseDto(savedResume);
    }

    public ResumeResponseDto getResumeByUserId(int userId) {

        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResumeNotFoundException(
                                "No resume found with user id "
                                        + userId));

        return resumeMapper.toResponseDto(resume);
    }

    public void deleteResume(int userId) throws IOException {

        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResumeNotFoundException(
                                "No resume found with user id "
                                        + userId));

        // Delete file from storage
        Files.deleteIfExists(
                Paths.get(resume.getFilePath()));

        // Delete DB record
        resumeRepository.delete(resume);
    }
}