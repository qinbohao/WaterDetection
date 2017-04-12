package com.waterdet.dao;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectMysql {

	 public static SqlSession connect()
	{
		String resource = "conf.xml"; //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
		InputStream is = ConnectMysql.class.getClassLoader().getResourceAsStream(resource);//����sqlSession�Ĺ���
		SqlSessionFactoryBuilder sqlbuilser=new SqlSessionFactoryBuilder();
		SqlSessionFactory sessionFactory = sqlbuilser.build(is);
		SqlSession session = sessionFactory.openSession();
		
		return session;
	}
	
}
