package com.tgb.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tgb.DB.DBConn;
import com.tgb.model.LoginUser;
import com.tgb.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	private String name;
	private String password;
	@Autowired
	private UserService userservice;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String check(){	
		
		Connection dbConn = DBConn.getConn();
		try {
			Statement stmt = dbConn.createStatement();
			String sql = "select * from user where name= \""+this.getName()+"\" and password= \""+this.getPassword()+"\"";
			System.out.println("sql :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			System.out.println("row "+rs.getRow());
			if(rs.getRow()==1)
				return "{\"info\"=\"1\",\"uid\"=\""+rs.getString("uid")+"\"}";//正确
			else {
				return "{\"info\"=\"0\",\"uid\"=\"0\"}";//用户名或者密码错误
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "{\"info\"=\"0\",\"uid\"=\"0\"}";//用户名或者密码错误
	}

	@RequestMapping("/login")
	public void execute(HttpServletRequest request,HttpServletResponse response)
	{			

		
		LoginController login= null;
		
		try {
//			String params = new String (request.getParameter("params").getBytes("iso-8859-1"),"utf-8");
//			System.out.println("params: "+params);
//			JSONObject obj = JSONObject.fromObject(params);			
//			login = (LoginController) JSONObject.toBean(obj, LoginController.class);
//			String tempstring = login.check();	
//			System.out.println("response: "+tempstring);
			LoginUser loginUser = new LoginUser();
			loginUser.setName("gdk");
			loginUser.setPassword("1234");
			List<LoginUser> list = userservice.login(loginUser);
			
			
			
			
			PrintWriter pw;
			pw = response.getWriter();
			if(list.size()==1)
				pw.println("list size 6666666666 =="+list.get(0).getName()+list.get(0).getPassword()+list.get(0).getId());
			else {
				pw.println("list size 77777777777 ==");
			}
			pw.close();
			
			//JSONObject temp = JSONObject.fromObject(response);
			//System.out.println("check json 格式"+temp.getString("info")+" hahaha "+ temp.getString("uid"));
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
			

		
	}

}
