package com.loveofworld.mvc.model.data;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.Util;


public class Params{

	private String PARAMS;

	public JSONObject ROOT;
	public Map<String, Object> COMMON;
	public Map<String, Object> DATA;
	public Map<String, Object> SEARCHINFO;
	
	public String getPARAMS() {
		return this.PARAMS;
	}

	public void setPARAMS(String pARAMS){
		//Log.getInstance().printLog(this, "asdlvkjalsdjvlaksjdvlkjasdlkvjlaksdj : " + pARAMS);
		this.PARAMS = pARAMS;
		this.ROOT   = (JSONObject) JSONValue.parse(this.PARAMS);
		this.COMMON = (Map<String, Object>)this.ROOT.get("COMMON");
		this.DATA   = (Map<String, Object>)this.ROOT.get("DATA");
		
		String searchInfoData = (String) this.DATA.get("SEARCHINFO");
		if(searchInfoData != null){
			this.SEARCHINFO = Util.getInstance().convertMapToJson((String) this.DATA.get("SEARCHINFO"));
			this.DATA.put("SEARCHINFO", this.SEARCHINFO);
		}
		else{
			this.SEARCHINFO = null;
		}
		
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	
	
}
