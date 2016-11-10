package com.loveofworld.db.abstracts;

import java.util.List;
import java.util.Map;

public interface DAOService{

	public boolean isTransaction();
	public void setTransaction(boolean isTransaction);
	public void commit(boolean isTransaction);
	public void rollback(boolean isTransaction);
	
	public boolean insertInfo(String sqlName, Object object);
	
	public boolean deleteInfo(String sqlName, Object object);
	
	public boolean updateInfo(String sqlName, Object object);
	
	public Map<String, Object> selectInfo(String sqlName, Object object);
	
	public List<Map<String, Object>> selectList(String sqlName, Object object);
	
//	public Map<Object, Object> select(String sqlName, Object object, String columnId);
	
}
/*
public abstract class DAOService {
	
	public abstract boolean isTransaction();
	public abstract void setTransaction(boolean isTransaction);
	public abstract void commit(boolean isTransaction);
	public abstract void rollback(boolean isTransaction);
	
	public abstract boolean insertInfo(String sqlName, Object object);
	
	public abstract boolean deleteInfo(String sqlName, Object object);
	
	public abstract boolean updateInfo(String sqlName, Object object);
	
	public abstract Map<String, Object> selectInfo(String sqlName, Object object);
	
	public abstract List<Map<String, Object>> selectListInfo(String sqlName, Object object);
	
	public abstract Map<Object, Object> selectMapInfo(String sqlName, Object object, String columnId);
}
*/