package com.dream.ccms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.ccms.controller.response.RestfulResponse;
import com.dream.ccms.dao.UserRepository;
import com.dream.ccms.entity.User;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserController {
    @Autowired 
	private UserRepository userRepository;
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public RestfulResponse create(@RequestBody User restUser) {
    	System.out.println(restUser.getPassword());	
    	User user=new User();
    	user=restUser;
    	userRepository.saveAndFlush(user);   	
    	return null;
    }

}
