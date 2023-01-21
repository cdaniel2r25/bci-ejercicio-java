package com.example.bci.demoApi.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bci.demoApi.models.User;
import com.example.bci.demoApi.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

		User user = userRepository.findByToken(token)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with token: " + token));

		return UserDetailsImpl.build(user);
	}

}
