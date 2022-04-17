package com.finalyear.finalyear.service;


import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.finalyear.finalyear.helper.RegistrationRequest;
import com.finalyear.finalyear.model.Role;
import com.finalyear.finalyear.model.User;
import com.finalyear.finalyear.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	

	@Override
	public User save(RegistrationRequest registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}
	 @Override
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	  @Override
	    @Modifying
	    public void updatePassword(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userRepository.save(user);
	    }
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			User user = userRepository.findByEmail(username);
			if(user == null) {
				throw new UsernameNotFoundException("Invalid username or password.");
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
		}
		
		private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role > roles){
			return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		}
	@Override
	public User getUserById(long id) {
		Optional<User>optional=userRepository.findById(id);
		User user =null;
		if(optional.isPresent()) {
			user=optional.get();
		}else {
			throw new RuntimeException("User Not FOund with this "+id);
		}
		return user;
	}

	
}
