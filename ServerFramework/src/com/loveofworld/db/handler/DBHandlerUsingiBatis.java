package com.loveofworld.db.handler;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.loveofworld.db.abstracts.DBHandlerService;
import com.loveofworld.system.util.Log;

public class DBHandlerUsingiBatis extends DBHandlerService{

	
	
	public Properties dbProperties;

	public DBHandlerUsingiBatis(Properties dbProperties){
		this.dbProperties = dbProperties;
	}


	@Override
	public SqlMapClient getDBHandler() {
		// TODO Auto-generated method stub
		
		SqlMapClient sqlMap = null;
		
		try {
			
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(dbProperties.getProperty("drivername"));
			basicDataSource.setUrl(dbProperties.getProperty("url"));
			basicDataSource.setUsername(dbProperties.getProperty("username"));
			basicDataSource.setPassword(dbProperties.getProperty("password"));
	
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX 
					                 + dbProperties.getProperty("handler.config.mapper");
			
			SqlMapClientFactoryBean sqlMapClientFactoryBean = new SqlMapClientFactoryBean();
			sqlMapClientFactoryBean.setDataSource(basicDataSource);
			sqlMapClientFactoryBean.setConfigLocation(new ClassPathResource(dbProperties.getProperty("handler.config")));
			sqlMapClientFactoryBean.setMappingLocations(resourcePatternResolver.getResources(packageSearchPath));		
			sqlMap = sqlMapClientFactoryBean.getObject();
		   
			Log.getInstance().printLog(this, "Setting SqlSessionFactory : " + sqlMap.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sqlMap;
	}

}
