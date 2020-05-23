package com.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDB {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

        //Creating a connection to the database
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-7NC8C0E5\\MSSQLSERVER;databaseName = EMPINFO;"
        		+ "integratedSecurity = true");	

        //Executing SQL query and fetching the result
        Statement st = conn.createStatement();
        String sqlStr = "select * from info";
        ResultSet rs = st.executeQuery(sqlStr);
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }       }

	}


