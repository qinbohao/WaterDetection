package com.waterdet.dao;



import org.apache.ibatis.session.SqlSession;

import java.util.List;
import com.waterdet.pojo.Sensor;


public class SensorDao {
	private List<Object> sensorlist;
	private Sensor sensor;
	public SensorDao(int number,String type)
	{
		sensor=new Sensor();
		sensor.setNumber(number);
		sensor.setType(type);
		String stat="com.waterdet.mappers.Sensormapper.getdata";
		SqlSession session = ConnectMysql.connect();
		sensorlist=(List<Object>)session.selectList(stat,sensor);
		session.close();
	}
	public List<Object> getSensorlist()
	{
		return sensorlist;
	}
}
