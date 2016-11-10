package com.loveofworld.db.dao;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.loveofworld.db.abstracts.DAOService;
import com.loveofworld.db.handler.DBHandlerUsingMybatis;
import com.loveofworld.system.util.Log;


public class DAOUsingMyBatis implements DAOService{
	
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private boolean isTransaction;
	
	public DAOUsingMyBatis(Properties dbProperties){
		this.sqlSessionFactory = new DBHandlerUsingMybatis(dbProperties).getDBHandler();
		Log.getInstance().printLog(this, "myBatis DAO Setting : " + this.sqlSessionFactory);
		this.setTransaction(false);
	}
	
	public boolean isTransaction() {
		return isTransaction;
	}

	public void setTransaction(boolean isTransaction) {
		this.isTransaction = isTransaction;
		
		if(this.isTransaction == true){
			this.setSqlSession(this.sqlSessionFactory.openSession());
		}
		else{
			this.sqlSession = null;
		}
	}
	
	public void commit(boolean isTransaction){
		sqlSession.commit();
		sqlSession.close();
		this.setTransaction(isTransaction);
	}
	
	public void rollback(boolean isTransaction){
		sqlSession.rollback();
		sqlSession.close();
		this.setTransaction(isTransaction);
	}

	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	private void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * SQL 처리 로직
	 * 1. sqlSession.insert(QueryId);
	 * 2. sqlSession.insert(QueryId, QueryArguments);
	 */	
	@Override
	public boolean insertInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = null;
		if(this.isTransaction == true){
			sqlSession = this.sqlSession;
		}
		else{
			sqlSession = this.sqlSessionFactory.openSession();
		}
				
		
		try {
			if(object != null){
				sqlSession.insert(sqlName, object);
			}
			else{
				sqlSession.insert(sqlName);
			}
		}
		catch (Exception ex) {
			Log.getInstance().printLog(this, ex.getMessage());
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
		
		if(this.isTransaction() == false){
			sqlSession.commit();
			sqlSession.close();
		}
		
		return true;
	}


	/**
	 * SQL 처리 로직
	 * 1. sqlSession.delete(QueryId);
	 * 2. sqlSession.delete(QueryId, QueryArguments);
	 */
	@Override
	public boolean deleteInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = null;
		if(this.isTransaction == true){
			sqlSession = this.sqlSession;
		}
		else{
			sqlSession = this.sqlSessionFactory.openSession();
		}
	
		try {
			if(object != null){
				sqlSession.delete(sqlName, object);
			}
			else{
				sqlSession.delete(sqlName);
			}
		}
		catch (Exception ex) {
			Log.getInstance().printLog(this, ex.getMessage());
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
		
		if(this.isTransaction() == false){
			sqlSession.commit();
			sqlSession.close();
		}
		
		return true;
	}

	
	/**
	 * SQL 처리 로직
	 * 1. sqlSession.update(QueryId);
	 * 2. sqlSession.update(QueryId, QueryArguments);
	 */
	@Override
	public boolean updateInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = null;
		if(this.isTransaction == true){
			sqlSession = this.sqlSession;
		}
		else{
			sqlSession = this.sqlSessionFactory.openSession();
		}
		
		try {
			if(object != null){
				sqlSession.update(sqlName, object);
			}
			else{
				sqlSession.update(sqlName);
			}
		}
		catch (Exception ex) {
			Log.getInstance().printLog(this, ex.getMessage());
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
		
		if(this.isTransaction() == false){
			sqlSession.commit();
			sqlSession.close();
		}
		
		
		return true;
	}

	
	/**
	 * SQL 처리 로직
	 * 1. sqlSession.select(QueryId, ResultHandler);
	 * 2. sqlSession.select(QueryId, QueryArguments, ResultHandler);
	 * 3. sqlSession.select(QueryId, QueryArguments, RowBounds, ResultHandler);
	 */
	@Override
	public Map<String, Object> selectInfo(String sqlName, Object object) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = null;
		if(this.isTransaction == true){
			sqlSession = this.sqlSession;
		}
		else{
			sqlSession = this.sqlSessionFactory.openSession();
		}

		Object resultObject = null;
		
		if(object != null){
			resultObject = sqlSession.selectOne(sqlName, object);
		}
		else{
			resultObject = sqlSession.selectOne(sqlName);
		}
		
		if(this.isTransaction() == false){
			sqlSession.close();
		}
		
		return (Map<String, Object>)resultObject;
	}

	
	/**
	 * SQL 처리 로직
	 * 1. sqlSession.selectList(QueryId);
	 * 2. sqlSession.selectList(QueryId, QueryArguments);
	 * 3. sqlSession.selectList(QueryId, QueryArguments, RowBounds);
	 */
	@Override
	public List<Map<String, Object>> selectList(String sqlName, Object object) {
		// TODO Auto-generated method stub
		
		SqlSession sqlSession = null;
		if(this.isTransaction == true){
			sqlSession = this.sqlSession;
		}
		else{
			sqlSession = this.sqlSessionFactory.openSession();
		}
		
		
		Object resultObject = null;
		
		if(object != null){
			resultObject = sqlSession.selectList(sqlName, object);
		}
		else{
			resultObject = sqlSession.selectList(sqlName);
		}
		
		if(this.isTransaction() == false){
			sqlSession.close();
		}
		
		return (List<Map<String, Object>>)resultObject;
	}
	
	
	/** 2016. 05. 11. : Loveofworld : myBatis 3.x에 대한 설정 후 selectMap()가 없으므로 일단은 주석처리. 
	 * SQL 처리 로직
	 * 1. sqlSession.selectList(QueryId, QueryArguments, columnId);
	
	@Override
	public Map<Object, Object> selectMapInfo(String sqlName, Object object, String columnId) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = null;
		if(this.isTransaction == true){
			sqlSession = this.sqlSession;
		}
		else{
			sqlSession = this.sqlSessionFactory.openSession();
		}
		
		Object resultObject = null;
		
		if(object != null){
			//resultObject = sqlSession.selectMap(sqlName, object, columnId);
		}
		else{
			//resultObject = sqlSession.selectMap(sqlName, null, columnId);
			resultObject = sqlSession.select(sqlName, null, columnId);
		}
		
		if(this.isTransaction() == false){
			sqlSession.close();
		}
		
		return (Map<Object, Object>)resultObject;
	}
	
	*/


}
