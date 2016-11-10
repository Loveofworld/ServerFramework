package com.loveofworld.mvc.view;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;


public class View {

	private static View instance;
	
	private View() {
		// TODO Auto-generated constructor stub
	}
	
	public static View getInstance(){
		if(instance == null){
			synchronized (View.class){
				if(instance == null){
					instance = new View();
				}
			}
		}
		
		return instance;
	}
	
	
	public ModelAndView getLoginView(){
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	
	public ModelAndView getErrorView(String errorCode, String errorMessage){
		ModelAndView mav = new ModelAndView();
		mav.addObject("ERROR_CODE", errorCode);
		mav.addObject("ERROR_MESSAGE", errorMessage);
//		mav.setViewName(Error Page Path);
		return mav;
	}
	
	
	public ModelAndView getView(String viewName, Map<String, Object> mapData){
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(mapData);
		mav.setViewName(viewName);
		return mav;
	}	

	public ModelAndView getView(String viewName){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
}
