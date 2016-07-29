package com.tgb.DB;

import java.sql.*;
//import com.mysql.jdbc.Connection;
import java.sql.Connection;

public class DBConn {
	
	public static Connection dbConn;
	public static String err = null;

	public DBConn() {
		dbConn = null;
	}
	static{
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			dbConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8", "root", "191954");
			if(!dbConn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");				
	        }  
		}catch(ClassNotFoundException e) {   
          //��ݿ������쳣����
          System.out.println("Sorry,can`t find the Driver!");           
          dbConn = null;
          err = e.toString();
          System.out.println("==========error:dbconn  " +err);
      }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
          System.out.println("��ݿ����ӳɹ���");
      }
		
		
	}

	public static Connection getConn() {		
		return dbConn;
	}
	
  
}
