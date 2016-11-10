package com.loveofworld.db.handler;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.loveofworld.db.abstracts.DBHandlerService;
import com.loveofworld.system.util.Log;

//@Configuration
//@MapperScan("com.vinflux.mobile.db.mybatis.config.oracle")
public class DBHandlerUsingMybatis extends DBHandlerService{
	
	public Properties dbProperties;

	public DBHandlerUsingMybatis(Properties dbProperties){
		this.dbProperties = dbProperties;
	}

	
	@Override
	public SqlSessionFactory getDBHandler() {
		// TODO Auto-generated method stub
		
		SqlSessionFactory sqlSessionFactory = null;
		try {
			
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(dbProperties.getProperty("drivername"));
			basicDataSource.setUrl(dbProperties.getProperty("url"));
			basicDataSource.setUsername(dbProperties.getProperty("username"));
			basicDataSource.setPassword(dbProperties.getProperty("password"));
			
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX 
					                 + dbProperties.getProperty("handler.config.mapper");
			
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(basicDataSource);
			sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(dbProperties.getProperty("handler.config")));
			sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(packageSearchPath));			
			sqlSessionFactory = sqlSessionFactoryBean.getObject();
			
			Log.getInstance().printLog(this, "Setting SqlSessionFactory : " + sqlSessionFactory.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}

	

	
}
