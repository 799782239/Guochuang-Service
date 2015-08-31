package com.servlet.SendData;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;
import com.Register.UserData;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		UserData userData = new UserData();
		int result = 0;
		String resultData = null;
		String judgeData = null;

		String username = request.getParameter("USERNAME");
		userData.setUsername(username);
		String password = request.getParameter("PASSWORD");
		userData.setPassword(password);
		String name = request.getParameter("NAME");
		userData.setName(name);
		String sex = request.getParameter("SEX");
		userData.setSex(sex);
		String phone = request.getParameter("PHONE");
		userData.setPhone(phone);
		System.out.println("UserData:" + userData.toString());

		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		
		PrintWriter out = response.getWriter();
		
		judgeData = jdbc.Register_Exist(username);
		if (judgeData.equals("not exist")) {
			result = jdbc.Register_AddToDB(userData);
			System.out.println("result:" + result);
			if (result == 1) {
				resultData = "OK";
			}
			else {
				resultData = "NO";
			}
			out.print(resultData);
		}
		else if (judgeData.equals("exist")) {
			out.print("exist");
		}
		else {
			out.print("not connect");
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
