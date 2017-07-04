package com.bowen.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//使用单例模式创建一个sessionFactory
public final class SessionFactory {
//配置SqlMapper.xml的路径
private String resource="com/bowen/source/SqlMapper.xml";
private SqlSessionFactory sqlSessionFactory=null;
private static SessionFactory sessionFactory=new SessionFactory();
private SessionFactory(){
	try {
		//读取配置文件
		Reader reader=Resources.getResourceAsReader(resource);
		//获取sqlSessionFactory对象
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
	} catch (IOException e) {
		e.printStackTrace();
	}	
}
public static SessionFactory getInstance() {  
    return sessionFactory;  
} 
public SqlSessionFactory getSqlSessionFactory() {  
    return sqlSessionFactory;  
}
}
