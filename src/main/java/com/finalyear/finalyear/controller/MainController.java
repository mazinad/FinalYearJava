package com.finalyear.finalyear.controller;

import com.finalyear.finalyear.model.User;
import com.finalyear.finalyear.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	Logger logger= LoggerFactory.getLogger(this.getClass());
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model,Authentication authentication) {
		String findUserName=authentication.getName();
		User user=userService.findByEmail(findUserName);
		model.addAttribute("userLogged", user);
		return "index";
	}
	@GetMapping("/api/editProfile")
	public String editProfiletest(Authentication auth, Model model)
	{
		String findUsername=auth.getName();
		User user=userService.findByEmail(findUsername);
		if(user==null){
			logger.error("An error occured while fetching data for the specific user");
		}
		model.addAttribute("userLogged",user);
		return "edit-profile";
	}

}
