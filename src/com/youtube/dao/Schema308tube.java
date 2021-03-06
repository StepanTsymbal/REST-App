package com.youtube.dao;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.util.ToJSON;

public class Schema308tube extends Oracle308tube {

	public int deletePC_PARTS(int pk) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("delete from PC_PARTS " +
											"where PC_PARTS_PK = ? ");
			
			query.setInt(1, pk);
			query.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}

	public int updatePC_PARTS(int pk, int avail) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {			
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("update PC_PARTS " +
											"set PC_PARTS_AVAIL = ? " +
											"where PC_PARTS_PK = ? ");
			
			query.setInt(1, avail);
			query.setInt(2, pk);
			query.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}

	public int insertIntoPC_PARTS(String PC_PARTS_TITLE, 
			String PC_PARTS_CODE, String PC_PARTS_MAKER, 
			String PC_PARTS_AVAIL, String PC_PARTS_DESC) 
										throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		try {
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("insert into PC_PARTS " +
					"(PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC) " +
					"VALUES ( ?, ?, ?, ?, ? ) ");

			query.setString(1, PC_PARTS_TITLE);
			query.setString(2, PC_PARTS_CODE);
			query.setString(3, PC_PARTS_MAKER);

			int avilInt = Integer.parseInt(PC_PARTS_AVAIL);
			query.setInt(4, avilInt);

			query.setString(5, PC_PARTS_DESC);
			query.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}

		return 200;
	}

	public JSONArray queryReturnBrandParts(String brand) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC " +
											"from PC_PARTS " +
											"where UPPER(PC_PARTS_MAKER) = ? ");
			
			query.setString(1, brand.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}

	public JSONArray queryReturnBrandItemNumber(String brand, int item_number) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC " +
											"from PC_PARTS " +
											"where UPPER(PC_PARTS_MAKER) = ? " +
											"and PC_PARTS_CODE = ?");
			
			query.setString(1, brand.toUpperCase());
			query.setInt(2, item_number);
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}

	public JSONArray queryAllPcParts() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC " +
											"from PC_PARTS");
			
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}

	public JSONArray queryCheckDbConnection() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME " +
											"from sys.dual");
			
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}
}