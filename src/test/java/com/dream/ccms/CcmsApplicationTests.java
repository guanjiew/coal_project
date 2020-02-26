package com.dream.ccms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.test.context.junit4.SpringRunner;

import com.dream.ccms.testBean.ComponentPerson;
import com.dream.ccms.testBean.TestConfig;
import com.dream.ccms.testBean.TestContext;
import com.dream.ccms.testBean.TestUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CcmsApplicationTests {
	/*@Test
	public void testBCry() {
		String encodeStr=BCrypt.hashpw("123", BCrypt.gensalt());
		System.out.println(encodeStr);
		
		  $2a$10$y84sX5.rF2xy1j601PLQVucRKgHGC/gsrlY/0429y7B40LsWZrrIO
		 * 
		boolean check=BCrypt.checkpw("123",  "$2a$10$y84sX5.rF2xy1j601PLQVucRKgHGC/gsrlY/0429y7B40LsWZrrIO");
		System.out.println(check);
	}*/

	@Test
	public void contextLoads() {
		 testApplicationCont();
				
      // testBean();
	}
	
	/*public void inject(String testUser) {
		System.out.println("testUser name:"+testUser.getUsername());
	}*/
	public void testApplicationCont() {
		TestContext newContext = new  TestContext();
		newContext.showNames();
		
	
	}
	
	public void testBean() {
		
				AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
				TestUser tu = (TestUser) context.getBean("testUserBean");		
				System.out.println("testUser name:"+tu.getUsername());
				ComponentPerson personObj = (ComponentPerson) context.getBean("componentPerson");
				System.out.println("componentPerson Obj:"+personObj);
				
				String[] definitionNames = context.getBeanDefinitionNames();
				for (String name : definitionNames) {
					System.out.println("BeanDefinitionName:"+name);
				}

	}
   
	/*public void runPython() {
		System.out.println("starting test Python...");
		Properties props = new Properties();
		props.put("python.home", "path to the Lib folder");
		props.put("python.console.encoding", "UTF-8");
		props.put("python.security.respectJavaAccessibility", "false");
		props.put("python.import.site", "false");
		Properties preprops = System.getProperties();
		PythonInterpreter.initialize(preprops, props, new String[0]);
		PythonInterpreter interpreter = new PythonInterpreter();
		System.out.println("Hello, brave new world");
		interpreter.exec("print (\"Hello Jython...!!!\" )");
		interpreter.execfile("rref.py");
		System.out.println("close test Python...");
		interpreter.close();
		// -Dpython.console.encoding=UTF-8 运行参数
	}*/

}
