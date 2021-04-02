package com.xigmad.baseapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xigmad.baseapp.entities.UserEntity;
import com.xigmad.baseapp.repositories.UsersRepositoryInt;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UsersRepositoryInt userRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByUsername(username);
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(userEntity.getRole()));
		UserDetails userDetails = new User(userEntity.getUsername(), userEntity.getPassword(), roles);
		return userDetails;
	}

}
