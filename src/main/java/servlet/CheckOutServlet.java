package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.DbCon;
import Dao.BuyNowDao;
import Model.BuyNow;
import Model.Cart;
import Model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			// retrive all cart product
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			// user authentication
			User auth = (User) request.getSession().getAttribute("auth");

			// check auth and cartlist
			if (cart_list != null && auth != null) {
				
				for (Cart c : cart_list) {
				// Prepare the order Object
					
					BuyNow order = new BuyNow();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formater.format(date));
               //  inisiating the dao class
					BuyNowDao odao = new BuyNowDao(DbCon.getconnection());
					
					// calling the insert method from dao
					boolean result = odao.insertorder(order);
					if (!result) 
						break;
			
				}
				cart_list.clear();
				response.sendRedirect("order.jsp");
			} else {
				if (auth == null)
					response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
