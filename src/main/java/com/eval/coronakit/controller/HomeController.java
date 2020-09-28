package com.eval.coronakit.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.User;
import com.eval.coronakit.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@GetMapping({"","/"})
	public String getHome() {
		return "index";
	}
	
	@RequestMapping("/header")
	public ModelAndView showHeader() {
		ModelAndView mv = new ModelAndView("header");
		mv=setAuthentication(mv);
		return mv;
	}
	
	@RequestMapping("/home")
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView("main-menu");
		mv=setAuthentication(mv);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user=new User();
		if (!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			user.setUsername(auth.getName());
			if("admin".equalsIgnoreCase(auth.getName())) {
				user.setRole("ROLE_ADMIN");
			} else {
				user.setRole("ROLE_USER");
			}
			user.setContact("1234567890");
			user.setEmail(auth.getName().toLowerCase() + "@user.com");
			user.setPassword((String) auth.getCredentials());
			session.setAttribute("username", user.getUsername());
			System.out.println("user name fetched :"+user.getUsername());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("contactnumber", user.getContact());
		}
		return mv;		
	}
	
	@GetMapping("/validate")
	public ModelAndView validateLoogin() {
		ModelAndView mv = new ModelAndView("main-menu");
		mv=setAuthentication(mv);
		return mv;		
	}
	
	@GetMapping("/register")
	public ModelAndView showRegisterationForm() {
		ModelAndView mv = new ModelAndView("register", "user", new User());
		mv=setAuthentication(mv);
		return mv;
	}
	
	@PostMapping("/register-save")
	public ModelAndView saveUser() {
		ModelAndView mv = new ModelAndView("main-menu");
		mv=setAuthentication(mv);
		return mv;
	}
	public ModelAndView setAuthentication(ModelAndView mv) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			String role = new ArrayList<>(auth.getAuthorities()).get(0).getAuthority();
			mv.addObject("unm", auth.getName());
			mv.addObject("role", role);
		}
		return mv;
	}
}
