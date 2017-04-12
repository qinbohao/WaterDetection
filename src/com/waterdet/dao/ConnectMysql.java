package com.waterdet.dao;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectMysql {

	 public static SqlSession connect()
	{
		String resource = "conf.xml"; //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = ConnectMysql.class.getClassLoader().getResourceAsStream(resource);//构建sqlSession的工厂
		SqlSessionFactoryBuilder sqlbuilser=new SqlSessionFactoryBuilder();
		SqlSessionFactory sessionFactory = sqlbuilser.build(is);
		SqlSession session = sessionFactory.openSession();
		
		return session;
	}
	
}
