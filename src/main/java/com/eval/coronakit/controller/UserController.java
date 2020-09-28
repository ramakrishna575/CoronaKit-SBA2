package com.eval.coronakit.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.ProductException;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CoronaKitService coronaKitService;
	
	@Autowired
	KitDetailService kitDetailService;
	
	@RequestMapping("/home")
	public String home() {
		return "user-home";
	}
	
	@RequestMapping("/show-kit")
	public ModelAndView showKit(HttpSession session,Model model) {	
		ModelAndView mv=new ModelAndView("show-kit");
		System.out.println("User" + SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("kitdetails", kitDetailService.getKitItemsByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		mv=setAuthentication(mv);
		return mv;
	}
	@RequestMapping("/show-cart")
	public ModelAndView showCart(HttpSession session,Model model) {	
		ModelAndView mv=new ModelAndView("show-cart");
		List<ProductMaster> cartaddedproducts=null;
		Map<Integer,Integer> hm=new HashMap<Integer,Integer>();
		
		List<ProductMaster> Addedproductstocart=(List<ProductMaster>)session.getAttribute("cartproduct");
		if (Addedproductstocart!=null)
		{		
		 cartaddedproducts=new ArrayList<ProductMaster>();
		  session.setAttribute("Qtymap", null);
		for(ProductMaster p:Addedproductstocart)
		{
			if (hm.containsKey(p.getId()))
			{
				hm.put(p.getId(), hm.get(p.getId())+1);
			}
			else
			{
				hm.put(p.getId(), 1);
				cartaddedproducts.add(p);				
			}
		}
		session.setAttribute("Qtymap", hm);
		session.setAttribute("cartaddedproduct", cartaddedproducts);
		}
		mv = setAuthentication(mv);
		return mv;
	}
	
	@RequestMapping("/show-list")
	public ModelAndView showList(Model model, HttpSession session) {
		ModelAndView mv=new ModelAndView("show-all-item-user");
		model.addAttribute("productlist", productService.getAllProducts());
		session.removeAttribute("cartproduct");
		session.removeAttribute("cartaddedproduct");
		session.removeAttribute("Qtymap");
		mv=setAuthentication(mv);
		
		return mv;
	}
	
	@RequestMapping("/add-to-cart/{productId}")
	public ModelAndView showKit(@PathVariable("productId") int productId, HttpSession session,Model model) {
		ProductMaster p;
		ModelAndView mv=new ModelAndView("show-all-item-user");
		List<ProductMaster> Addedproducts;
		p=productService.getProductById(productId);
		Addedproducts= (List<ProductMaster>) session.getAttribute("cartproduct");
		if (Addedproducts==null)
		{
			Addedproducts=new ArrayList<ProductMaster>();
		}
		if(p!=null)
		{
			Addedproducts.add(p);
		}
		session.setAttribute("cartproduct", Addedproducts);
		model.addAttribute("productlist",productService.getAllProducts());
		mv=setAuthentication(mv);
		return mv;
	}
	
	@RequestMapping("/checkout")
	public ModelAndView checkout(Model model) {
		ModelAndView mv = new ModelAndView("checkout-address");
		mv = setAuthentication(mv);
		return mv;
	}
	
	@RequestMapping("/finalize")
	public ModelAndView finalizeOrder(@RequestParam("Address1") String address, Model model,HttpSession session) throws ProductException {
		ModelAndView mv;
		
			KitDetail k;
			int Totalamount=0;
			List<ProductMaster> Addedproductstocart=(List<ProductMaster>)session.getAttribute("cartaddedproduct");
			Map<Integer,Integer> hm=(Map<Integer,Integer>)session.getAttribute("Qtymap");
			for(ProductMaster p:Addedproductstocart) {
				Totalamount=Totalamount+(hm.get(p.getId())*p.getCost());
			}
			
			
			model.addAttribute("Address1",address);		
		
			CoronaKit kit=new CoronaKit();
	
			kit.setDeliveryAddress(address);
			kit.setOrderDate(LocalDate.now());
			kit.setTotalAmount(Totalamount);
			coronaKitService.saveKit(kit);
			System.out.println(kit.getId());
			for(ProductMaster p:Addedproductstocart) {
				k= new KitDetail(kit.getId(),p.getId(),p.getProductName(),hm.get(p.getId()),(hm.get(p.getId())*p.getCost()),SecurityContextHolder.getContext().getAuthentication().getName());
				kitDetailService.addKitItem(k);
			}
			List<KitDetail> details=kitDetailService.getAllKitItemsOfAKit(kit.getId());
			model.addAttribute("kitdetails", details);
			session.setAttribute("Totalamount", Totalamount);
			session.setAttribute("OrderID", kit.getId());
			mv=new ModelAndView("show-summary");

			//mv=new ModelAndView("checkout-address");
		
		mv=setAuthentication(mv);
		return mv;
	}
	
	@RequestMapping("/delete/{itemId}")
	public ModelAndView deleteItem(@PathVariable("itemId") int itemId, HttpSession session,Model model) {
		ModelAndView mv=new ModelAndView("show-cart");
		List<ProductMaster> Addedproductstocart=(List<ProductMaster>)session.getAttribute("cartaddedproduct");
		List<ProductMaster> RefreshCartAddedProducts=new ArrayList<ProductMaster>();
		Map<Integer,Integer> hm=(Map<Integer,Integer>)session.getAttribute("Qtymap");

		for(ProductMaster p:Addedproductstocart)
				{
			if (p.getId()==itemId)	
			{
				hm.remove(itemId);				
			}	
			else
			{
				RefreshCartAddedProducts.add(p);
			}
				}
		session.setAttribute("Qtymap", hm);
		session.setAttribute("cartaddedproduct", RefreshCartAddedProducts);
		mv =setAuthentication(mv);
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
