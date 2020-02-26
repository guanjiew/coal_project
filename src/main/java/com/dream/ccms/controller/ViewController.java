package com.dream.ccms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class ViewController {

	/**
     * Model本身不能设置页面跳转的url地址别名或者物理跳转地址，
     * 那么可以通过控制器方法的返回值来设置跳转url("myThymeleaf")
     * 地址别名或者物理跳转地址 * 
     * @param model   一个接口， 其实现类为ExtendedModelMap，继承了ModelMap类
     * @return 跳转url地址别名或者物理跳转地址
     */
	@RequestMapping("/thy")
    public String test(Model model){
		model.addAttribute("name", "testname");
		model.addAttribute("host", "127.0.0.1");
		return "myThymeleaf";		
	}
	
	 /**
     * ModelAndView主要有两个作用 设置转向地址和传递控制方法处理结果数据到结果页面
     * @return 返回一个模板视图对象
     */
	@RequestMapping(value="/t1",method=RequestMethod.GET)
    public ModelAndView modeltest(){
		ModelAndView myview=new ModelAndView();
		myview.addObject("name", "testnamemode");
		myview.addObject("host", "127.0.0.1");
		myview.setViewName("myThymeleaf");
		return myview;		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/t2",method=RequestMethod.GET)
    public String mptest(Map map){		
		map.put("name", "testmap");
		map.put("host", "127.0.0.1");	
		return "myThymeleaf";		
	}
/*	@RequestMapping("/t3")
	public String session(HttpSession session){	
		session.setAttribute("name", "HttpSession");
		session.setAttribute("host", "127.0.0.1");	
		return "myThymeleaf";
	}*/
	@RequestMapping("/t4")
	public String request(HttpServletRequest request){	
		request.setAttribute("name", "request");
		request.setAttribute("host", "127.0.0.1");	
		return "myThymeleaf";
	}
	
}
