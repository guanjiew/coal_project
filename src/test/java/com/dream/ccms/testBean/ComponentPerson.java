package com.dream.ccms.testBean;

import org.springframework.stereotype.Component;

@Component
public class ComponentPerson {
	private String name;
	private int age;
 
	public ComponentPerson() {
		this.name="configution person name";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    
}
