package com.finalyear.finalyear.controller;

import com.finalyear.finalyear.model.User;
import com.finalyear.finalyear.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class testController {
	@Autowired
	private UserService userService;
	@GetMapping("/layout")
	public String Strtest(Model model,Authentication authentication) {
		String findUserName=authentication.getName();
		User user=userService.findByEmail(findUserName);
		model.addAttribute("userLogged", user);
		return "layout";
	}
}
