package com.dream.ccms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.ccms.authorization.TokenManager;
import com.dream.ccms.controller.request.UserRequest;
import com.dream.ccms.controller.response.APIResponseStatusCode;
import com.dream.ccms.controller.response.RestfulResponse;
import com.dream.ccms.dao.UserRepository;
import com.dream.ccms.entity.User;

@RestController
@CrossOrigin

public class UserLogin {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RestfulResponse login(@RequestBody UserRequest restUser) {
		System.out.println("login...");
		if (this.isValidUer(restUser)) {
			System.out.println("starting CreateToken...");
			String userPID = restUser.getUsername();
			String userToken = TokenManager.createToken(userPID);
			Map<String, String> result = new HashMap<String, String>();
			result.put("token", userToken);
			result.put("userId", userPID);
			return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, result);
		}
		return null;
	}

	private boolean isValidUer(UserRequest ac) {
		String name = ac.getUsername();
		String pass = ac.getPassword();
		System.out.println("name:"+name+"password:"+pass);
		List<User> userList = userRepository.findByNameAndPassword(name, pass);
		if (!userList.isEmpty())
			return true;
		else
			return false;
	}

}
