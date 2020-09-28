package com.eval.coronakit.controller;



import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.ProductException;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductService productService;
	
	@RequestMapping("/home")
	public ModelAndView displayHome() {
		ModelAndView mv = new ModelAndView("admin-home");
		mv=setAuthentication(mv);
		return mv;
	}
	
	@GetMapping("/product-entry")
	public ModelAndView productEntry(Model model) {
		ModelAndView mv = new ModelAndView("add-new-item");
		model.addAttribute("product", new ProductMaster());
		mv=setAuthentication(mv);
		return mv;
	}
	
	@PostMapping("/product-save")
	public ModelAndView productSave(@ModelAttribute("product") @Valid ProductMaster product, BindingResult result, Model model ) throws ProductException {
		ModelAndView mv=null;
		if (!result.hasErrors()) {
			productService.addNewProduct(product);
			model.addAttribute("msg", "Product got added successfully");
			mv = new ModelAndView("admin-home");
		} else {
			mv = new ModelAndView("add-new-item");
		}
		mv=setAuthentication(mv);
		return mv;
	}
	

	@GetMapping("/product-list")
	public ModelAndView productList(Model model) {
		ModelAndView mv = new ModelAndView("show-all-item-admin");
		model.addAttribute("productlist", productService.getAllProducts());	
		mv=setAuthentication(mv);
		return mv;
	}
	
	@GetMapping("/product-delete/{productId}")
	public ModelAndView productDelete(@PathVariable("productId") int productId, Model model) throws ProductException {
		ModelAndView mv = new ModelAndView("show-all-item-admin");
		productService.deleteProduct(productId);
		model.addAttribute("deleteconfirmationmsg", "Product " + productId + " got deleted successfully");
		model.addAttribute("productlist", productService.getAllProducts());	
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
