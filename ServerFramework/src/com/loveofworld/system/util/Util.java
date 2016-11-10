package com.loveofworld.system.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.loveofworld.mvc.model.data.Params;
import com.loveofworld.mvc.model.message.JsonMessage;
import com.loveofworld.mvc.view.View;
import com.loveofworld.system.ConstValue;

public class Util {

	private static Util instance;
	
	private Util() {
		// TODO Auto-generated constructor stub
	}
	
	public static Util getInstance(){
		if(instance == null){
			synchronized (Util.class){
				if(instance == null){
					instance = new Util();
				}
			}
		}
		
		return instance;
	}
	
	
	public Object getReturnObject(int requestType, HttpServletRequest request, String code, String msg){
			
		Object result = null;
			
	    if(requestType == ConstValue.REQUEST_JSON){
	    	
	    	Params params = new Params();
	    	if(request.getParameter("PARAMS") != null){
	    		params.setPARAMS(request.getParameter("PARAMS"));
	    	}
			
	    	JsonMessage jsonMessage = new JsonMessage();
			jsonMessage.setSERVICE("SessionCheck");
			jsonMessage.setSTATUS(code);
			jsonMessage.setMSG(msg);
			jsonMessage.setParams(params);
			result = jsonMessage.convertResponseMessage();
		}
		else{
		    	result = View.getInstance().getErrorView(code, msg);
		    }
	    
	    return result;
	}
	
	public Properties getProperties(String resourcePath){
		
		String resourceFullPath = resourcePath;//"com/vinflux/mobile/system/properties/"+resourcePath;
		
		ClassPathResource resource = new ClassPathResource(resourceFullPath);
		Properties properties = null;
		try {
			properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	public Properties getSystemProperties(String resourcePath){
		String systemResourcePath = "com/vinflux/mobile/system/properties/"+resourcePath;
		return getInstance().getProperties(systemResourcePath);
	}
	
	
	public Map<String, Object> convertMapToJson(String jonsData){
		return (Map<String, Object>) JSONValue.parse(jonsData);
	}
	
	public String authNumberGen(String key){
		
		String authNum = null;
		try {
			authNum = EncryptionUtils.encryptAES(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return authNum;
		
	};
}
