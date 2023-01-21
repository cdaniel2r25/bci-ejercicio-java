package com.example.bci.demoApi.services;

import org.springframework.security.core.Authentication;

import com.example.bci.demoApi.models.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto userRequest) throws Exception;
	UserDto getUser(Authentication auth);
}
