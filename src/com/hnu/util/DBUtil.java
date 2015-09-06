package com.hnu.util;

import java.sql.*;


public class DBUtil {
	
	/*public static void main(String []args){
		System.out.print(DBUtil.getConn());
	}*/
	static JdbcConfig jc = XmlConfigReader.getInstance().getJdbcConfig();
	
	private DBUtil() {}
	
	static{
		try {
			Class.forName(jc.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jc.getUrl(), jc.getUsername(), jc.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Statement getStmt(Connection conn){
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static PreparedStatement getPstmt(Connection conn, String sql){
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public static ResultSet getRS(Connection conn, String sql){
		ResultSet rs = null;
		try {
			rs = conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return rs;
	}
	
	public static ResultSet getRS(Statement stmt, String sql){
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return rs;
	}
	
	public static void close(Connection conn){
		if(conn != null){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement stmt){
		try {
			if(stmt != null){
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
