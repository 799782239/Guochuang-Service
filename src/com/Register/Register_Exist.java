package com.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register_Exist {
	public static String judge(String username, Connection connection) {
		// ����SQL����ִ�ж���
		PreparedStatement pstmt = null;
		// �����ѯ���صĽ������
		ResultSet resultSet = null;
		try {
			connection.createStatement();
			String sql = "select * from userdata where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				
					return "exist";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "not exist";
	}
}
