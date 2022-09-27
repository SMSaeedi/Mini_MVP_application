package com.at.check24.service;

import com.at.check24.dto.UserDto;
import com.at.check24.exception.NotFoundException;
import com.at.check24.model.User;
import com.at.check24.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${service.user.exception.notFound}")
    String userNotFound;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    public UserService(UserRepository userRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserDto findUserById(Integer userId) {
      return userRepository.findById(userId)
              .map(this::convertToDto)
              .orElseThrow(() -> new NotFoundException(userNotFound));
    }

    private UserDto convertToDto(User movie) {
        return mapper.convertValue(movie, UserDto.class);
    }
}