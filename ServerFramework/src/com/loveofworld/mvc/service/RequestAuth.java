package com.loveofworld.mvc.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.loveofworld.mvc.common.abstracts.CommonService;
import com.loveofworld.mvc.model.data.Params;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.util.EncryptionUtils;
import com.loveofworld.system.util.Log;

public class RequestAuth extends CommonService{

	public final int AUTH_SUCCESS =  1;
	public final int AUTH_FAIL    = -1;
	public final int AUTH_NODATA  =  2;
	public final int AUTH_PASS    =  0;
	
	public RequestAuth(){
	
	}
	
	public int isCheckAuth(HttpServletRequest request){
	    
		int isCheckAuth = AUTH_FAIL;
		
		String auth = request.getParameter("AUTH");
		
		Log.getInstance().printLog(this, "Reqest Session User Info : auth : " + auth);
		
		
		if(auth == null){
			return isCheckAuth;
		
		}
		
		String auth_des = null;
		try {
			//auth_des = EncryptionUtils.decryptAES(auth);
			Log.getInstance().printLog(this, "Reqest Session User Info : auth_des : " + auth_des);
			//isCheckAuth = AUTH_SUCCESS;
			/*
			UserService userService = new UserService();
			Map<String, Object> queryMap = new HashMap<String, Object>(); 
			queryMap.put("MBRBARCODE", auth_des);
			Map<String, Object> userInfo = userService.selectUserInfo(queryMap);
			if(userInfo != null){
				isCheckAuth = AUTH_SUCCESS;
			}
			else{
				isCheckAuth = AUTH_FAIL;
			}
			*/
			isCheckAuth = AUTH_FAIL;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return isCheckAuth;
		}

		return isCheckAuth;
	}
	
	
}
