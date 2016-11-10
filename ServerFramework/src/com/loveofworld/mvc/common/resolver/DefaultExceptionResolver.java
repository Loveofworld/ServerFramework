package com.loveofworld.mvc.common.resolver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.loveofworld.system.ConstValue;
import com.loveofworld.system.MessageValue;
import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.Util;


public class DefaultExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
		// TODO Auto-generated method stub
		/*
		String requestUrl = request.getRequestURL().toString();
        Log.getInstance().printLog(this, "Request URL : " + requestUrl);
        int requestType = 0;
        
        if(requestUrl.contains(".json")){
        	requestType = ConstValue.REQUEST_JSON;
        	Log.getInstance().printLog(this, "Request Type : JSON");
        }
        else{
        	requestType = ConstValue.REQUEST_VIEW;
        	Log.getInstance().printLog(this, "Request Type : VIEW");
        }
        
		Log.getInstance().printExceptionLog(this, exception.fillInStackTrace());
		ModelAndView result = new ModelAndView();
		result =  Util.getInstance().getReturnObject(requestType, ConstValue.ERROR_CODE_FAIL, MessageValue.MSG_ERROR);
			return result;	
		*/
		return null;
	}

	


}
