<%@page import="Connection.DbCon"%>
<%@page import="Dao.BuyNowDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@page import="Model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	  
	  DecimalFormat dcf =new DecimalFormat("#.##");
	  request.setAttribute("dcf", dcf);
      User auth = (User)request.getSession().getAttribute("auth");
      List<BuyNow> orders =null;
    
    if(auth!= null)
    {
    request.setAttribute("auth", auth);
    orders = new BuyNowDao(DbCon.getconnection()).userorders(auth.getId());
    	
    }
    else
    {
    	response.sendRedirect("login.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  //  List<Cart> cartProduct = null;
    if (cart_list != null) {
   
    	request.setAttribute("cart_list", cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Page</title>
<%@include file="include/head.jsp"%>
</head>
<body>
	<%@include file="include/NavBar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>

				</tr>
			</thead>
			<tbody>
				<% if(orders != null)
			  {
				  for(BuyNow o : orders)
				  {%>
				<tr>
					<td><%= o.getDate() %></td>
					<td><%= o.getName() %></td>
					<td><%= o.getCategory() %></td>
					<td><%= o.getQuantity() %></td>
					<td><%= dcf.format(o.getPrice()) %></td>
					<td><a class="btn btn-sm btn-danger"
						href="CancelOrderServlet?id=<%= o.getOrderid() %>">Cancel</a></td>
				</tr>
				<%}
			  }%>

			</tbody>
		</table>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>