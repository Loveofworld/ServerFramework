package com.loveofworld.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.loveofworld.db.abstracts.DAOService;
import com.loveofworld.db.handler.DBHandlerUsingiBatis;
import com.loveofworld.system.util.Log;

public class DAOUsingiBatis implements DAOService{

	private SqlMapClient sqlMapClient;
	private SqlMapSession sqlMapSession;
	
	private Connection connection;
	private boolean isTransaction;
	
	public DAOUsingiBatis(Properties dbProperties){
		this.sqlMapClient = new DBHandlerUsingiBatis(dbProperties).getDBHandler();
		Log.getInstance().printLog(this, "myBatis DAO Setting : " + this.sqlMapClient);
		this.setTransaction(false);
	}
	
	
	/**
	 * SQL 처리 로직
	 * 1. sqlMapClient.insert(QueryId);
	 * 2. sqlMapClient.insert(QueryId, QueryArguments);
	 */
	@Override
	public boolean insertInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		
		SqlMapSession sqlMapSession = null;
		Connection connection = null;
		if(this.isTransaction == true){
			sqlMapSession = this.sqlMapSession;
			connection    = this.connection;
		}
		else{
			sqlMapSession = this.sqlMapClient.openSession();
			try {
				connection    = this.sqlMapClient.getCurrentConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			sqlMapSession.insert(sqlName, object);
			
		}
		catch (Exception ex) {
			Log.getInstance().printLog(this, ex.getMessage());
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			};
			return false;
		}
		
		if(this.isTransaction() == false){
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}

	
	/**
	 * SQL 처리 로직
	 * 1. sqlMapClient.delete(QueryId);
	 * 2. sqlMapClient.delete(QueryId, QueryArguments);
     */
	@Override
	public boolean deleteInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		SqlMapSession sqlMapSession = null;
		Connection connection = null;
		if(this.isTransaction == true){
			sqlMapSession = this.sqlMapSession;
			connection    = this.connection;
		}
		else{
			sqlMapSession = this.sqlMapClient.openSession();
			try {
				connection    = this.sqlMapClient.getCurrentConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			sqlMapSession.delete(sqlName, object);
			
		}
		catch (Exception ex) {
			Log.getInstance().printLog(this, ex.getMessage());
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			};
			return false;
		}
		
		if(this.isTransaction() == false){
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}

	
	/**
	 * SQL 처리 로직
	 * 1. sqlMapClient.update(QueryId);
	 * 2. sqlMapClient.update(QueryId, QueryArguments);
	 */
	@Override
	public boolean updateInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		
		SqlMapSession sqlMapSession = null;
		Connection connection = null;
		if(this.isTransaction == true){
			sqlMapSession = this.sqlMapSession;
			connection    = this.connection;
		}
		else{
			sqlMapSession = this.sqlMapClient.openSession();
			try {
				connection    = this.sqlMapClient.getCurrentConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			sqlMapSession.update(sqlName, object);
			
		}
		catch (Exception ex) {
			Log.getInstance().printLog(this, ex.getMessage());
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			};
			return false;
		}
		
		if(this.isTransaction() == false){
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}

	
	/**
	 * SQL 처리 로직
	 * 1. sqlMapClient.queryForObject(QueryId, ResultHandler);
	 * 2. sqlMapClient.queryForObject(QueryId, QueryArguments, ResultHandler);
	 * 3. sqlMapClient.queryForObject(QueryId, RowBounds, ResultHandler);
	 * 4. sqlMapClient.queryForObject(QueryId, QueryArguments, RowBounds, ResultHandler);
     */
	@Override
	public Map<String, Object> selectInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		
		Map<String, Object> resultObject = null;
		
		try {
			resultObject = (Map<String, Object>)this.sqlMapClient.queryForObject(sqlName, object);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return resultObject;
	}

	
	/**
	 * SQL 처리 로직
	 * 1. sqlMapClient.queryForList(QueryId, ResultHandler);
	 * 2. sqlMapClient.queryForList(QueryId, QueryArguments, ResultHandler);
	 * 3. sqlMapClient.queryForList(QueryId, RowBounds, ResultHandler);
	 * 4. sqlMapClient.queryForList(QueryId, QueryArguments, RowBounds, ResultHandler);
     */
	@Override
	public List<Map<String, Object>> selectList(String sqlName, Object object) {
		// TODO Auto-generated method stub
		
		List<Map<String, Object>> resultObject = null;
		
		try {
			resultObject = (List<Map<String, Object>>)this.sqlMapClient.queryForList(sqlName, object);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return resultObject;
	}

	
	/** 2016. 05. 11. : Loveofworld : selectMapInfo 사안함. queryForMap은 사용할지 고민해봐야 함.
	 * SQL 처리 로직
	 * 1. sqlSession.queryForMap(QueryId, QueryArguments, columnId);
     
	@Override
	public Map<Object, Object> selectMapInfo(String sqlName, Object object, String columnId) {
		// TODO Auto-generated method stub

		Map<Object, Object> resultObject = null;
		
		try {
			resultObject = (Map<Object, Object>)this.sqlMapClient.queryForMap(sqlName, object, columnId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return resultObject;
	}

	*/
	
	
	@Override
	public boolean isTransaction() {
		// TODO Auto-generated method stub
		return this.isTransaction;
	}


	@Override
	public void setTransaction(boolean isTransaction) {
		// TODO Auto-generated method stub
		this.isTransaction = isTransaction;
		
		if(this.isTransaction == true){
			this.sqlMapSession = this.sqlMapClient.openSession();
			try {
				this.connection = this.sqlMapClient.getCurrentConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			this.sqlMapSession = null;
			this.connection = null;
		}
		
	}


	@Override
	public void commit(boolean isTransaction) {
		// TODO Auto-generated method stub
		try {
			connection.rollback();
			connection.close();
			this.setTransaction(isTransaction);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	@Override
	public void rollback(boolean isTransaction) {
		// TODO Auto-generated method stub
		
		try {
			connection.rollback();
			connection.close();
			this.setTransaction(isTransaction);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
