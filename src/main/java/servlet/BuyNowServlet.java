package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class BuyNowServlet
 */
@WebServlet("/BuyNowServlet")
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			User auth =(User)request.getSession().getAttribute("auth");
			if(auth != null)
			{
				String productid = request.getParameter("id");
				int productQuantiy =Integer.parseInt(request.getParameter("quantity"));
				if(productQuantiy <=0)
				{
					productQuantiy =1;
					
				}
				BuyNow orderModel=new BuyNow();
				
				orderModel.setId(Integer.parseInt(productid));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(productQuantiy);
			    orderModel.setDate(formater.format(date));
			   
			    BuyNowDao orderDao=new BuyNowDao(DbCon.getconnection());
			    boolean result = orderDao.insertorder(orderModel);
			      
	          if(result)
	          {
	        	  ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
	                      if(c.getId()== Integer.parseInt(productid))
	                      {
	                    	  cart_list.remove(cart_list.indexOf(c));
	                    	  break;
	                      }
						}
					}
	        	  response.sendRedirect("order.jsp");  
	          }
	          else
	          {
	        	  out.println("order failed");
	          }
			}
			else
			{
				response.sendRedirect("login.jsp");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
