package com.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register_ToDB {
	public static int AddToDB(UserData userData, Connection connection) {
		// 定义sql语句的执行对象
		PreparedStatement pstmt = null;
		// 返回结果
		int result = 0;
		try {
			connection.createStatement();
			String sql = "insert into userdata values(NULL,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userData.getUsername());
			pstmt.setString(2, userData.getPassword());
			pstmt.setString(3, userData.getName());
			pstmt.setString(4, userData.getPhone());
			pstmt.setString(5, userData.getSex());
			result = pstmt.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
