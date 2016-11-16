package com.loveofworld.mvc.common.abstracts;

import com.loveofworld.db.abstracts.DAOService;
import com.loveofworld.db.handler.DBHandler;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.util.Log;

public abstract class CommonService {

	protected DAOService adminDAO = null;
	protected Log log = null; 
	
	protected CommonService(){
		this.setAdminDAO(DBHandler.getInstance().getDBHandler(ConstValue.RESOURCE_PATH + "db.admin.properties"));
		//this.setEmsDAO(DBHandler.getInstance().getDBHandler(ConstValue.RESOURCE_PATH + "db.ems.Properties"));
		//this.setCloudMySqlDAO(DBHandler.getInstance().getDBHandler(ConstValue.RESOURCE_PATH + "db.googlecloud.mysql.Properties"));
		this.setLog(Log.getInstance());
	}
	
	protected void setAdminDAO(DAOService adminDAO) {
		this.adminDAO = adminDAO;
	}

	protected DAOService getAdminDAO() {
		return adminDAO;
	}
	
	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
	
	
	

}
