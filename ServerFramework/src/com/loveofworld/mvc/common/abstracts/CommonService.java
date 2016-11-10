package com.loveofworld.mvc.common.abstracts;

import com.loveofworld.db.abstracts.DAOService;
import com.loveofworld.db.handler.DBHandler;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.util.Log;

public abstract class CommonService {

	protected DAOService adminDAO = null;
	protected DAOService emsDAO = null;
	protected DAOService cloudMySqlDAO = null;
	protected Log log = null; 
	
	protected CommonService(){
		this.setAdminDAO(DBHandler.getInstance().getDBHandler(ConstValue.RESOURCE_PATH + "db.admin.Properties"));
		//this.setEmsDAO(DBHandler.getInstance().getDBHandler(ConstValue.RESOURCE_PATH + "db.ems.Properties"));
		//this.setCloudMySqlDAO(DBHandler.getInstance().getDBHandler(ConstValue.RESOURCE_PATH + "db.googlecloud.mysql.Properties"));
		this.setLog(Log.getInstance());
	}
	
	protected void setAdminDAO(DAOService adminDAO) {
		this.adminDAO = adminDAO;
	}

	protected void setEmsDAO(DAOService emsDAO) {
		this.emsDAO = emsDAO;
	}

	protected DAOService getAdminDAO() {
		return adminDAO;
	}

	protected DAOService getEmsDAO() {
		return emsDAO;
	}
	
	

	public DAOService getCloudMySqlDAO() {
		return cloudMySqlDAO;
	}

	public void setCloudMySqlDAO(DAOService cloudMySqlDAO) {
		this.cloudMySqlDAO = cloudMySqlDAO;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
	
	
	

}
