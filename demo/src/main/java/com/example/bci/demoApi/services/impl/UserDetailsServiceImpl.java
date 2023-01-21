package com.example.bci.demoApi.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bci.demoApi.models.User;
import com.example.bci.demoApi.repository.UserRepository;
import com.example.bci.demoApi.services.UserDetailsTokenService;

@Service
public class UserDetailsServiceImpl implements UserDetailsTokenService, UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
		User user = userRepository.findByToken(token)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with token: " + token));

		return UserDetailsImpl.build(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
