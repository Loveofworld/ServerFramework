package com.loveofworld.mvc.controller.noauth.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.loveofworld.mvc.controller.abstracts.ViewController;
import com.loveofworld.mvc.view.View;
import com.loveofworld.system.util.Log;
import com.loveofworld.test.MemberInfoBean;
import com.loveofworld.test.TestExcelView;

@Controller
public class MainControllerSample extends ViewController{

	
	@RequestMapping("/Main/Test.do")
	public ModelAndView mainMainInfo(HttpServletRequest request){
		return  View.getInstance().getView("test/app.html");
	}
	

}
