package com.finalyear.finalyear.service;



import com.finalyear.finalyear.helper.RegistrationRequest;
import com.finalyear.finalyear.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService{
	User save(RegistrationRequest registrationDto);
	void updatePassword(User user);
	User findByEmail(String email);
	User getUserById(long id);
}
