package com.loveofworld.db.handler;

import java.util.HashMap;
import java.util.Properties;

import com.loveofworld.db.abstracts.DAOService;
import com.loveofworld.db.dao.DAOUsingMyBatis;
import com.loveofworld.db.dao.DAOUsingiBatis;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.ThrowException;
import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.Util;


public class DBHandler{

	private static DBHandler instance;
	
	private HashMap<String, DAOService> map_DAOService =  null;
	
	private DBHandler() {
		// TODO Auto-generated constructor stub
		if(map_DAOService == null){
			map_DAOService = new HashMap<String, DAOService>();
		}
		else{
			map_DAOService.clear();
		}
	}
	
	public static DBHandler getInstance(){
		if(instance == null){
			synchronized (DBHandler.class){
				if(instance == null){
					instance = new DBHandler();
				}
			}
		}
		
		return instance;
	}
	
	
	public DAOService getDBHandler(String propertiesPath){

		String[] propertiesPathInfo = propertiesPath.split("/");
		String propertiesName = propertiesPathInfo[propertiesPathInfo.length - 1];
		
		DAOService temp_DAOService = getInstance().map_DAOService.get(propertiesName);
		
		if(temp_DAOService == null){
			
			Properties dbProperties = Util.getInstance().getProperties(propertiesPath);
			
			if(dbProperties == null){
				try {
					throw new ThrowException(this, ConstValue.ERROR_CODE_FAIL, "DB Properties is NULL !!");
				} catch (ThrowException e) {
					// TODO Auto-generated catch block
					Log.getInstance().printLog(this, "ThrowException : " + e.getMessage());
					return null;
				}
			}
			
			String type_Handler = dbProperties.getProperty("handler.type");
			if(type_Handler.equals("ibatis")){
				temp_DAOService = setDAOHandlerUsingiBatis(dbProperties);
			}
			else if(type_Handler.equals("mybatis")){
				temp_DAOService = setDAOHandlerUsingMyBatis(dbProperties);
			}
			else{
				try {
					throw new ThrowException(this, ConstValue.ERROR_CODE_FAIL, "Handler Type is ERROR : " + type_Handler);
				} catch (ThrowException e) {
					// TODO Auto-generated catch block
					Log.getInstance().printLog(this, "ThrowException : " + e.getMessage());
					return null;
				}
			}
		}
		
		getInstance().map_DAOService.put(propertiesName, temp_DAOService);

		return getInstance().map_DAOService.get(propertiesName);
		//return temp_DAOService;
	}

	
	public DAOService setDAOHandlerUsingMyBatis(Properties dbProperties) {
		DAOService dAOHandler = new DAOUsingMyBatis(dbProperties);
		return dAOHandler;
	}
	
	public DAOService setDAOHandlerUsingiBatis(Properties dbProperties) {
		DAOService dAOHandler = new DAOUsingiBatis(dbProperties);
		return null;
	}

}
