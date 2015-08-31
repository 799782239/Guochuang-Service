package com.notice.GetData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetNotice {

	public GetNotice() {
		// TODO Auto-generated constructor stub
	}

	public static List<Notice> getNoticeByReceiver(String receiver,
			Connection connection) {
		List<Notice> NoticeList = new ArrayList<Notice>();
		// 定义sql语句的执行对象
		PreparedStatement pstmt = null;
		// 定义查询返回的结果集合
		ResultSet resultSet = null;
		try {
			connection.createStatement();
			String sql = "select * from notice where Receiver=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, receiver);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Notice classNotice = new Notice();
				classNotice.setId(resultSet.getInt("Id"));
				classNotice.setEmphasis(resultSet.getString("Emphasis"));
				classNotice.setNoticeTime(resultSet.getString("NoticeTime"));
				classNotice.setPlace(resultSet.getString("Place"));
				classNotice.setDetails(resultSet.getString("Details"));
				classNotice.setSender(resultSet.getString("Sender"));
				NoticeList.add(classNotice);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return NoticeList;
	}
}
