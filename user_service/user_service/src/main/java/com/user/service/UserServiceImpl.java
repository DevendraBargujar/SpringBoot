package com.user.service;

import java.util.List;

import com.user.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	List<User> list = List.of(new User(121L,"Ram","123456"),
			new User(122L,"Shyam","123456789"),
			new User(123L,"Laxman","123456987")
			);
	@Override
	public User getUser(Long id) {		
		return list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null);
	}

}
