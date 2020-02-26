package com.dream.ccms.testBean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.dream.ccms.dao.CoalRepository;
import com.dream.ccms.entity.CokingCoal;
@Component

public class TestContext {
	private ApplicationContext applicationContext;

	private CoalRepository coalRepository;
	
	private void showNames( ApplicationContext applicationContext) {
		String[] definitionNames = MycontextAware.getBeanDefinitionNames();
		for (String name : definitionNames) {
			System.out.println("BeanDefinitionName:"+name);
		}
	}
	public void showNames() {
		this.showNames(applicationContext);
	}
	public void dbThread() {
		this.dbThread(coalRepository);
		/*Dbquery dbquery=new Dbquery(coalRepository);
		//dbquery.setCoalRepository();
		//dbquery.run();
		Thread queryThread=new Thread(dbquery);
		queryThread.start();*/
	}
	public void dbThread(CoalRepository coalRespository) {
		List<CokingCoal> coalList = coalRepository.findAll();
		for (CokingCoal coal : coalList) {
			System.out.println("coal name:" + coal.getName());
		}
	}
}
