package com.loveofworld.mvc.controller.noauth.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

/** 2016. 05. 11. : Cho Yongkyun : 해당 사용안하는지 정확하게 파악이 필요.
import jsx3.app.Model;
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.loveofworld.mvc.controller.abstracts.ViewControllerSevice;
import com.loveofworld.mvc.model.message.XMLMessage;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.MessageValue;
import com.loveofworld.system.util.Log;

@Controller
public class XMLMessageControllerSample extends ViewControllerSevice{
    
	@RequestMapping("/XMLMessageTest.xml")
	public ModelAndView XMLMessageTest(HttpServletRequest request, ModelAndView model){

		ArrayList<Map<String, Object>> reponseData = new ArrayList<Map<String, Object>>();
		
		Log.getInstance().printLog(this, "====================================================");
	
		for(int i = 0 ; i < 10 ; i++){
			
			Map<String, Object> mapData = new HashMap<String, Object>();
			mapData.put("index"+i, "value"+i);
			reponseData.add(mapData);
			
			Set<Entry<String, Object>> entrySet = mapData.entrySet();
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				Log.getInstance().printLog(this, "List index " + i + " : " + entry.getKey() + " = " + entry.getValue());
			}
			
		}
		
		Log.getInstance().printLog(this, "====================================================");
		
		XMLMessage xMLMessage = new XMLMessage();
		xMLMessage.setSERVICE("XMLMessageTest");
		xMLMessage.setSTATUS(ConstValue.ERROR_CODE_SUCCESS);
		xMLMessage.setMSG(MessageValue.MSG_SUCCESS);
		xMLMessage.setMapDataList(reponseData);
	   //  Example user = new Example("8410151032214", "HDM", 29);
		
		
        
  
		
		return new ModelAndView("xmlClass", "testdo", reponseData);
	}
}
