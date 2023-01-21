package com.example.bci.demoApi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bci.demoApi.models.dto.UserDto;
import com.example.bci.demoApi.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userRequest) throws Exception {

		UserDto user = userService.createUser(userRequest);

		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/byToken", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getUserByToken(Authentication auth) throws Exception {

		UserDto user = userService.getUser(auth);
		return ResponseEntity.ok(user);
	}
}
