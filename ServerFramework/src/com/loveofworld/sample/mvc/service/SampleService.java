package com.loveofworld.sample.mvc.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.loveofworld.mvc.common.abstracts.CommonService;

@Service("sampleService")
public class SampleService  extends CommonService{

	
	public ArrayList<Map<String, Object>> getCenterInfo(Map<String, Object> parameterMap){
		ArrayList<Map<String, Object>> centerInfoList = (ArrayList<Map<String, Object>>) this.getAdminDAO().selectList("com.loveofworld.admin.master.SelectCenterInfo", parameterMap);
		return centerInfoList;
	}
	
	
	public ArrayList<Map<String, Object>> getAdminCodeInfo(Map<String, Object> parameterMap){
		ArrayList<Map<String, Object>> centerInfoList = (ArrayList<Map<String, Object>>) this.getAdminDAO().selectList("com.loveofworld.admin.master.SelectCodeInfo", parameterMap);
		return centerInfoList;
	}
	
}
