package com.servlet.SendData;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;
import com.getUserInfo.FriendListToXML;
import com.getUserInfo.UserInfo;

public class GetUserInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GetUserInfoServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//获得从客户端传来的数据
		String userName = request.getParameter("USER_NAME");
		String userIP = request.getParameter("IP");
		String Local_longitude = request.getParameter("Local_longitude");
		String Local_latitude = request.getParameter("Local_latitude");
		
		//将横纵坐标转换为Double形式
		Double userLocal_longitude = Double.parseDouble(Local_longitude);
		Double userLocal_latitude = Double.parseDouble(Local_latitude);
		
		// 获得notice的list
		JDBC jdbc = new JDBC();
		// 连接数据库
		jdbc.ConnectToSQL();

		String result = null;
		List<UserInfo> FriendList = new ArrayList<UserInfo>();
		FriendList = jdbc.getuserFriendList(userName,userIP,userLocal_longitude,userLocal_latitude);
		FriendListToXML friendToXml = new FriendListToXML();
		result = friendToXml.ToXml(FriendList,userName);
		

		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
