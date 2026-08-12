package com.srihari.jobpilot.mapper;

import com.srihari.jobpilot.dto.UserRequestDto;
import com.srihari.jobpilot.dto.UserResponseDto;
import com.srihari.jobpilot.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto requestDto) {

        User user = new User();

        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setPhone(requestDto.getPhone());

        return user;
    }

    public UserResponseDto toResponseDto(User user) {

        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPhone(user.getPhone());
        responseDto.setRole(user.getRole());
        responseDto.setCreatedAt(user.getCreatedAt());
        responseDto.setUpdatedAt(user.getUpdatedAt());

        return responseDto;
    }
}