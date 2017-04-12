package com.waterdet.dao;
import org.apache.ibatis.session.SqlSession;



import com.waterdet.pojo.*;
public class UserDao {
	private User user;
	
	public UserDao(String userID)
	{
		user=new User();
		user.setUserid(userID);
		String stat="com.waterdet.mappers.Usermapper.getuser";
		SqlSession session = ConnectMysql.connect();
		user=(User)session.selectOne(stat,user);
		session.close();
	}
	public String getUserPassword() {
		String password=null;
		if(user!=null)
			password=user.getPassward();
		
		return password;	
	}

}
