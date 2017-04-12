package com.waterdet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.waterdet.dao.SensorDao;
import com.waterdet.pojo.Sensor;
import com.blade.kit.json.JSONArray;
import com.blade.kit.json.JSONKit;
import com.blade.kit.json.JSONObject;
/**
 * Servlet implementation class Getdata
 */
@WebServlet("/Getdata")
public class Getdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Getdata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String sensor=request.getParameter("Sensor");
		int number= Integer.parseInt(request.getParameter("number"));
		if(sensor!=null){
			SensorDao sd=new SensorDao(number,sensor);
			List<Object> sdlist=sd.getSensorlist();
			ArrayList<String> send=new ArrayList<String>();
			Iterator it = sdlist.iterator();
			while(it.hasNext()) {
			 Sensor se=(Sensor)it.next();
			 String temp=se.getData();
			 send.add(temp);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(sensor, send);
			System.out.println(jsonObject.toString());
			PrintWriter out = response.getWriter();  
			out.print(jsonObject.toString());
	        out.flush();
			//System.out.println(jsonObject.toString());
		}
			
		
			
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
