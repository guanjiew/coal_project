package com.dream.ccms.testBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan (value="com.dream.ccms.testBean")
//@EnableWebMvc extends WebMvcConfigurationSupport 
public class TestConfig {

	public TestConfig() {
		// TODO Auto-generated constructor stub
	}
	@Bean
	public TestUser testUserBean() {
		return new TestUser();
	}

}
