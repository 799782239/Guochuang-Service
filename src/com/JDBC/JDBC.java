package com.JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.Login.Authentication;
import com.Register.Register_Exist;
import com.Register.Register_ToDB;
import com.Register.UserData;
import com.getUserInfo.Updata_GetUserInfoInDB;
import com.getUserInfo.UserInfo;
import com.notice.GetData.GetNotice;
import com.notice.GetData.Notice;

public class JDBC {

	private Connection connection;

	public JDBC() {
		// TODO Auto-generated constructor stub
	}

	public void ConnectToSQL() {
		String USERNAME = "root";
		String PASSWORD = "admin";
		String URL = "jdbc:mysql://localhost:3306/greenroad";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Notice> getNotice(String classid) {
		List<Notice> noticelist = new ArrayList<Notice>();
		noticelist = GetNotice.getNoticeByReceiver(classid, connection);
		return noticelist;
	}

	public List<UserInfo> getuserFriendList(String username, String userIP,
			Double userLocal_longitude, Double userLocal_latitude) {
		List<UserInfo> FriendList = new ArrayList<UserInfo>();
		FriendList = Updata_GetUserInfoInDB.getFriendList(username, userIP,
				userLocal_longitude, userLocal_latitude, connection);
		return FriendList;
	}

	public String Login_Authentication(String username, String password) {
		String result = null;
		result = Authentication.judge(username, password, connection);
		return result;
	}

	public int Register_AddToDB(UserData userData) {
		int result = 0;
		result = Register_ToDB.AddToDB(userData, connection);
		return result;
	}

	public String Register_Exist(String username) {
		// TODO Auto-generated method stub
		String judge = null;
		judge = Register_Exist.judge(username, connection);
		return judge;
	}
}
