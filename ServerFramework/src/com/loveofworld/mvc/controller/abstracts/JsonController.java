package com.loveofworld.mvc.controller.abstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.flex.remoting.RemotingInclude;

import com.loveofworld.mvc.model.data.Params;
import com.loveofworld.mvc.model.message.JsonMessage;
import com.loveofworld.system.util.Log;


/*

 - Method prototype sample about return JSON - Map<String, Object> :
 	@RequestMapping(value="ServicName/MethodName.json", method='RequestMethod.POST or RequestMethod.GET'T)
 	@ResponseBody
 	public Map functionName(HttpServletRequest request, Params params){
 		
 		....

		return getJsonMsg(String status, String msg, Params params, Object resultData){
 	}
 
 - myBatis or iBatis Database Handler
 	boolean DAOService.insertInfo(String sqlName, Object object);
 	boolean DAOService.deleteInfo(String sqlName, Object object);
 	boolean DAOService.updateInfo(String sqlName, Object object);
 	Object  DAOService.selectInfo(String sqlName, Object object);
 	Object  DAOService.selectListInfo(String sqlName, Object object);
 	Object  DAOService.selectMapInfo(String sqlName, Object object, String columnId);

 */

public class JsonController {
	
	protected Map<String, Object> getJsonMsg(String status, String msg, Params params, Object resultData){
	
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setSTATUS(status);
		jsonMessage.setMSG(msg);
		jsonMessage.setParams(params);
	
		if(resultData != null){
			if ( resultData instanceof ArrayList ) {
				jsonMessage.setMapDataList((ArrayList<Map<String, Object>>)resultData);
			} 
			else if (resultData instanceof Map) {
				jsonMessage.addMapData((Map<String, Object>) resultData);
			} 
			else if (resultData instanceof String) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("RESULT", resultData);
				jsonMessage.addMapData(resultMap);
			}
		}
	
		return jsonMessage.convertResponseMessage();
		
	}
	
	protected Log getLog(){
		return Log.getInstance();
	}
	
	protected void printParamsData(Object object, Params parmas){
		
		this.getLog().printLog(this,"=========================");
		if(parmas.COMMON != null){
			this.getLog().printLog(object, "Params COMMON Log : ");
			this.getLog().printLog(object, parmas.COMMON.toString());
			
		}
		else{
			this.getLog().printLog(object, "Params COMMON Log : null");
		}
		
		if(parmas.DATA != null){
			this.getLog().printLog(object, "Params DATA Log : ");
			this.getLog().printLog(object, parmas.DATA.toString());
		}
		else{
			this.getLog().printLog(object, "Params DATA Log   : null");			
		}
		
		if(parmas.SEARCHINFO != null){
			this.getLog().printLog(object, "Params SEARCHINFO Log : ");
			this.getLog().printLog(object, parmas.SEARCHINFO.toString());
		}
		else{
			this.getLog().printLog(object, "Params SEARCHINFO Log   : null");			
		}
		
		this.getLog().printLog(this,"=========================");
	}
	
	
	
	@RemotingInclude // BDS
	//@RequestMapping(value="/messagebroker/amf", method=RequestMethod.GET)
	//@ResponseBody
	public Map amfHandlerAdapter(HttpServletRequest request, Params params) {
		
		Log.getInstance().printLog(this, "돼냐 ㅋ");
		System.out.println("alksjdlkajsdlajsdlajsdlja;lsdja;lskdj;alksjd;lakjsd;lkajsd;lkajsda");
		return null;
	}

	
}
