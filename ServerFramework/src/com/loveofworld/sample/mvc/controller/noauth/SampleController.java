package com.loveofworld.sample.mvc.controller.noauth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveofworld.mvc.controller.abstracts.JsonController;
import com.loveofworld.mvc.model.data.Params;
import com.loveofworld.sample.mvc.service.SampleService;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.MessageValue;
import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.Util;

@Controller("SampleController")
@RemotingDestination
public class SampleController extends JsonController{

	
	@Resource(name="sampleService") 
	private SampleService sampleService = null;
	
	
	@RequestMapping(value="/SampleController/test.json", method=RequestMethod.GET)
	@ResponseBody
	public Map jsonMessageTest(HttpServletRequest request, Params params){
		
		Log.getInstance().printLog(this, "====================================================");
		
		Properties dbProperties = Util.getInstance().getSystemProperties("db.admin.properties");
		
		String dbType        = dbProperties.getProperty("db.type");
		String dbHandlerType = dbProperties.getProperty("db.handler");
		Log.getInstance().printLog(this,"db.type    : " + dbType);
		Log.getInstance().printLog(this,"db.handler : " + dbHandlerType);

		Log.getInstance().printLog(this, "====================================================");
		
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("LAKEY", "KOR");
		
		ArrayList<Map<String, Object>> centerInfoList = this.sampleService.getCenterInfo(parameterMap);
		responseMap.put("CenterInfoList", centerInfoList);
		
		ArrayList<Map<String, Object>> adminCodeInfoList = this.sampleService.getAdminCodeInfo(parameterMap);
		responseMap.put("AdminCodeInfoList", centerInfoList);
		
		Log.getInstance().printLog(this, "====================================================");
		
		return getJsonMsg(ConstValue.ERROR_CODE_SUCCESS, MessageValue.MSG_SUCCESS, params, responseMap);
		
		
	}
	


}
