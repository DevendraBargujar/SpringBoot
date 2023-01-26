package com.user.controller;

import java.util.List;

import com.user.model.AuthenticationRequest;
import com.user.model.AuthenticationResponse;
import com.user.model.RegisterRequest;
import com.user.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.user.entity.Contact;
import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired(required = true)
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") Long userId) {
		User user = userService.getUser(userId);
		List<Contact> list = this.restTemplate.getForObject("http://contact-service/contact/user/"+userId, List.class);
		user.setContact(list);
		return user;
	}
}
