package com.example.bci.demoApi.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bci.demoApi.config.jwt.JwtUtil;
import com.example.bci.demoApi.exceptions.BadRequestException;
import com.example.bci.demoApi.models.Phones;
import com.example.bci.demoApi.models.User;
import com.example.bci.demoApi.models.dto.UserDto;
import com.example.bci.demoApi.models.dto.UserPhonesDto;
import com.example.bci.demoApi.repository.UserRepository;
import com.example.bci.demoApi.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtil jwtUtils;

	@Override
	public UserDto createUser(UserDto userRequest) throws Exception {
		if (userRepository.existsByEmail(userRequest.getEmail())) {
			throw new BadRequestException("Error: El correo ya esta registrado");
		}

		User user = new User();
		user.setDisabled(Boolean.FALSE);
		user.setEmail(userRequest.getEmail());
		user.setLastLogin(LocalDateTime.now());
		user.setName(userRequest.getName());
		user.setPassword(encoder.encode(userRequest.getPassword()));
		user.setToken("");
		user.setTypeToken("Bearer");

		List<Phones> phones = new ArrayList<>();
		for (UserPhonesDto phone : userRequest.getPhones()) {
			phones.add(new Phones(phone.getNumber(), phone.getCitycode(), phone.getContrycode()));
		}
		user.setPhones(phones);
		user = userRepository.save(user);

		String jwt = jwtUtils.generateTokenLimited(user, user.getEmail());

		user.setToken(jwt);
		user = userRepository.save(user);
		return new User().normalizeObj(user);
	}

	@Override
	public UserDto getUser(Authentication auth) {
		UserDetailsImpl userAuth = (UserDetailsImpl) auth.getPrincipal();

		Optional<User> user = userRepository.findById(userAuth.getId());
		if (user.isPresent()) {
			return new User().normalizeObj(user.get());
		} else {
			return new UserDto();
		}
	}

}
