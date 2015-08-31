package com.getUserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Updata_GetUserInfoInDB {

	public Updata_GetUserInfoInDB() {
		// TODO Auto-generated constructor stub
	}

	public static List<UserInfo> getFriendList(String username, String userIP,
			Double userLocal_longitude, Double userLocal_latitude,
			Connection connection) {
		List<UserInfo> FriendList = new ArrayList<UserInfo>();

		// 实例化定义sql语句的执行对象
		PreparedStatement updataUserdataPstmt = null;
		Statement updataFriendstmt = null;
		PreparedStatement getFriendPstmt = null;

		// 定义查询返回的结果集合
		ResultSet resultSet = null;

		try {
			// 刷新userdata表信息

			String updatauserdataSQL = "update userdata set IP=?,Local_longitude=?,Local_latitude=?,Online='true' where User_name=?";
			connection.createStatement();
			updataUserdataPstmt = connection.prepareStatement(updatauserdataSQL);
			updataUserdataPstmt.setString(1, userIP);
			updataUserdataPstmt.setDouble(2, userLocal_longitude);
			updataUserdataPstmt.setDouble(3, userLocal_latitude);
			updataUserdataPstmt.setString(4, username);
			updataUserdataPstmt.executeUpdate();
			
			
			// 刷新用户friend表信息

			String updataFriendSQL = "update "
					+ username
					+ " set Online=(select Online from userdata where "
					+ username
					+ ".friend=userdata.User_name),"
					+ "IP=(select IP from userdata where "
					+ username
					+ ".friend=userdata.User_name),"
					+ "Local_longitude=(select Local_longitude from userdata where "
					+ username
					+ ".friend=userdata.User_name),"
					+ "Local_latitude=(select Local_latitude from userdata where "
					+ username + ".friend=userdata.User_name)";
			updataFriendstmt = connection.createStatement();
			updataFriendstmt.executeUpdate(updataFriendSQL);

			// 执行查询语句
			connection.createStatement();
			String getFriendSQL = "select * from " + username
					+ " where Online='true'";
			getFriendPstmt = connection.prepareStatement(getFriendSQL);
			resultSet = getFriendPstmt.executeQuery();

			while (resultSet.next()) {
				UserInfo userdata = new UserInfo();
				userdata.setID(resultSet.getInt("ID"));
				userdata.setUsername(resultSet.getString("friend"));
				userdata.setName(resultSet.getString("Name"));
				userdata.setPhone(resultSet.getString("Phone"));
				userdata.setSex(resultSet.getString("Sex"));
				userdata.setIP(resultSet.getString("IP"));
				userdata.setLocal_longitude(resultSet
						.getDouble("Local_longitude"));
				userdata.setLocal_latitude(resultSet
						.getDouble("Local_latitude"));
				FriendList.add(userdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return FriendList;
	}

}
