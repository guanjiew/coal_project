package com.dream.ccms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@EnableAutoConfiguration
@EnableWebMvc
@ServletComponentScan(basePackages = "com.dream.ccms.filter")
*/
public class CcmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcmsApplication.class, args);
	}

}
