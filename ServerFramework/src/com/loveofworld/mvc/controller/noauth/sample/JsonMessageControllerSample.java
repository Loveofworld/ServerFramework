package com.loveofworld.mvc.controller.noauth.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveofworld.mvc.controller.abstracts.JsonControllerService;
import com.loveofworld.mvc.model.data.Params;
import com.loveofworld.mvc.model.message.JsonMessage;
import com.loveofworld.mvc.service.GasStationService;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.MessageValue;
import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.Util;

@Controller("jsonMessageControllerSample")
@RemotingDestination
public class JsonMessageControllerSample extends JsonControllerService{

	@RequestMapping(value="/jsonMessageControllerSample/test.json", method=RequestMethod.GET)
	@ResponseBody
	public Map jsonMessageTest(HttpServletRequest request, Params params){

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
		/*
		String dbType = Util.getInstance().getSystemProperties().getProperty("db.type");
		String dbHandlerType = Util.getInstance().getSystemProperties().getProperty("db.handler");
		
		Log.getInstance().printLog(this,"db.type    : " + dbType);
		Log.getInstance().printLog(this,"db.handler : " + dbHandlerType);
		*/
		Log.getInstance().printLog(this, "====================================================");
		
		/*
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setSTATUS(ConstValue.ERROR_CODE_SUCCESS);
		jsonMessage.setMSG(MessageValue.MSG_SUCCESS);
		jsonMessage.setParams(params);
		jsonMessage.setMapDataList(reponseData);
		
		return jsonMessage.convertResponseMessage();
		*/
		
		return getJsonMsg(ConstValue.ERROR_CODE_SUCCESS, MessageValue.MSG_SUCCESS, params, reponseData);
		
		
	}
	
	
	


	@RequestMapping(value="/jsonMessageControllerSample/setGasPriceInfo.json", method=RequestMethod.GET)
	@ResponseBody
	public Map setGasPriceInfo(HttpServletRequest request, Params params){

		ArrayList<Map<String, Object>> reponseData = new ArrayList<Map<String, Object>>();
		
		GasStationService gasStationService = new GasStationService();
		gasStationService.setGasPriceInfo();
	
		return getJsonMsg(ConstValue.ERROR_CODE_SUCCESS, MessageValue.MSG_SUCCESS, params, reponseData);
		
		
	}
	
	
	
	

	@RequestMapping(value="/jsonMessageControllerSample/getGasPriceInfo.json", method=RequestMethod.GET)
	@ResponseBody
	public Map test_CloudMySql(HttpServletRequest request, Params params){

		GasStationService gasStationService = new GasStationService();
		List<Map<String, Object>> reponseData = gasStationService.getGasDailyInfo();
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setSTATUS(ConstValue.ERROR_CODE_SUCCESS);
		jsonMessage.setMSG(MessageValue.MSG_SUCCESS);
		jsonMessage.setParams(params);
		jsonMessage.setMapDataList(reponseData);
		
		return jsonMessage.convertResponseMessage();
		
		
	}
	


}
