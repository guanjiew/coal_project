package com.dream.ccms.testBean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class MycontextAware implements ApplicationContextAware {
	private static ApplicationContext applicationContext=null;

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		MycontextAware.applicationContext=applicationContext;
		

	}
	public static String[] getBeanDefinitionNames() {
		return applicationContext.getBeanDefinitionNames();
	}
	public static Object getBeanByName(String beanName) {
		if (applicationContext == null) {
			return null;
		}
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

}
