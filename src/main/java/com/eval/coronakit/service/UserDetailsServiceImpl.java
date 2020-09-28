package com.eval.coronakit.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.UserRepository;
import com.eval.coronakit.entity.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository appUserRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails userDetails = null;
		com.eval.coronakit.entity.User user=appUserRepo.findByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("No Such User Found  ");
		}
		
		
		
		List<GrantedAuthority> authorities= new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));		

		return userDetails;
	}
	
	public User save(User user) {		
		return appUserRepo.save(user);
	}

}
