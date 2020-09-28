package com.eval.coronakit.controller;

import java.util.ArrayList;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.exception.ProductException;

@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(ProductException.class)
	public ModelAndView ErrorPage(ProductException exp)
	{
		ModelAndView mv = new ModelAndView("invalid-page","errMsg",exp.getMessage());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			String role = new ArrayList<>(auth.getAuthorities()).get(0).getAuthority();
			mv.addObject("unm", auth.getName());
			mv.addObject("role", role);
		}
		return mv;		
	}

}
