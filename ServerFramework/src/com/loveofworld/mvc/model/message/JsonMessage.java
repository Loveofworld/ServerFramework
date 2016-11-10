package com.loveofworld.mvc.model.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loveofworld.mvc.model.abstracts.MessageService;

public class JsonMessage extends MessageService{

	private List<Map<String, Object>> mapDataList;
	
	public JsonMessage(){
		this.initMapDataList();
	}
	
	public void initMapDataList() {
		this.mapDataList = new ArrayList<Map<String, Object>>();
	}
	
	public void setMapDataList(List<Map<String, Object>> mapDataList) {
		this.mapDataList = mapDataList;
	}
	
	public List<Map<String, Object>> getMapDataList() {
		return mapDataList;
	}
	
	public void addMapData(Map<String, Object> mapData) {
		this.mapDataList.add(mapData);
	}
	
	public void removeMapData(Map<String, Object> mapData) {
		this.mapDataList.remove(mapData);
	}
	
	public void removeMapData(int index) {
		this.mapDataList.remove(index);
	}

	@Override
	public Map<String, Object> convertResponseMessage() {
		// TODO Auto-generated method stub
		Map<String, Object> resultDataMessage = new HashMap<String, Object>();
		resultDataMessage.put("DATA" , getMapDataList());
		resultDataMessage.put("SIZE" , getMapDataList().size());
		
		Map<String, Object> returnResultsMessage = new HashMap<String, Object>();
		returnResultsMessage.put("STATUS"  , getSTATUS());
		returnResultsMessage.put("MSG"     , getMSG());
		returnResultsMessage.put("DATA"    , resultDataMessage);
		
		Map<String, Object> parmsMessage = new HashMap<String, Object>();
		if(getParams() != null){
			parmsMessage.put("COMMON" , getParams().COMMON);
			parmsMessage.put("DATA"   , getParams().DATA);
		}
		
		Map<String, Object> mapMessage = new HashMap<String, Object>();
		mapMessage.put("RESULT" , returnResultsMessage);
		mapMessage.put("PARAMS" , parmsMessage);
		
		return mapMessage;
	}

}
