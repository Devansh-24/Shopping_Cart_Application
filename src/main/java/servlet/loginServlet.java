package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.DbCon;
import Dao.UserDao;
import Model.User;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/user-login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;chaeset=UTF-8");
		try(PrintWriter out= response.getWriter()){
			 
			String email = request.getParameter("login-email");
			String password =request.getParameter("login-password");
			
			 UserDao udao = new UserDao(DbCon.getconnection());
			 User user = udao.userlogin(email, password);
			  if(user!= null)
			  {
				  out.print("user login");
				  request.getSession().setAttribute("auth", user);
				  response.sendRedirect("index.jsp");
				  
						  }
			  else
			  {
				  out.print("user login failed");
			  }
	
	}

	}
	
}