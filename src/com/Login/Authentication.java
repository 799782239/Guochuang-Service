package com.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authentication {

	public Authentication() {
		// TODO Auto-generated constructor stub
	}

	public static String judge(String username, String password,
			Connection connection) {
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
				String SQLpw = resultSet.getString("Pass_word");
				if (SQLpw.equals(password)) {
					return "pass";
				} else {
					return "not pass";
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "not find";
	}

}
