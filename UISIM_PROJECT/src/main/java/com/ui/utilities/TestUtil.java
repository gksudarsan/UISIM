package com.ui.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUtil {
	
	public static long PAGE_LOAD_TIMEOUT=1500;
	public static long IMPLICIT_WAIT=50;
	public static void main(String[] args) throws SQLException {
		try {
			  // Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			  Class.forName("com.ibm.db2.jcc.DB2Driver");
			} catch (ClassNotFoundException e) {
			     e.printStackTrace();
			}
	    //String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:sslConnection=true";
		//String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:sslConnection=true;";
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		//String user = "NDKSK4";
		String user = "NDKSK4";
		String password = "Tata@1234";
		Connection con=DriverManager.getConnection( url, user, password);
		System.out.println("Connected Successfully");
		String query1 = "SELECT SSN FROM T_CLAIMANT";
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query1);
		while(rs.next())
		{System.out.println(rs.getString("SSN"));
		}
		con.close();
		

	}
}
