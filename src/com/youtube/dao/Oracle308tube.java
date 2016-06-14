package com.youtube.dao;

import java.sql.Connection;

import javax.naming.*;
import javax.sql.*;

public class Oracle308tube {
	
	private static DataSource oracle308tube = null;
	private static Context context = null;
	
	public static DataSource oracle308tubeConn() throws Exception{
		
		if (oracle308tube != null){
			return oracle308tube;
		}
		
		try{
			if (context == null){
				context = new InitialContext();
			}
			
			oracle308tube = (DataSource) context.lookup("308Oracle");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return oracle308tube;
	}
	
	protected static Connection oraclePcPartsConnection(){
		
		Connection conn = null;
		try{
			conn = oracle308tubeConn().getConnection();
			return conn;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
