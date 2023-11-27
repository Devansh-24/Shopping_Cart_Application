<%@page import="java.text.DecimalFormat"%>
<%@page import="Connection.DbCon"%>
<%@page import="Dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat dcf =new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");

if (auth != null) {
	request.setAttribute("auth", auth);

}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pdao = new ProductDao(DbCon.getconnection());
	
	cartProduct = pdao.getCartProduct(cart_list);
	
	double Total=pdao.getTotalCartPrice(cart_list);
	
	request.setAttribute("cart_list", cart_list);
	
	request.setAttribute("total",Total);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart_Page</title>
<%@include file="include/head.jsp"%>
</head>
<body>
	<%@include file="include/NavBar.jsp"%>
	<style type="text/css">
.table tbodytd {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price :$ ${(total>0)?dcf.format(total):0}</h3>
			<a class="mx-3 btn btn-primary" " href="CheckOutServlet">Check Out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {%>

				<tr>
					<td><%= c.getName() %></td>
					<td><%= c.getCategory()%></td>
					<td><%= dcf.format(c.getPrice())%>$</td>
					<td>
						<form action="BuyNowServlet" method="post" class="form-inline">
					
							<input type="hidden" name="id" value="<%=c.getId() %>"
								class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre" href="QuantityincrementDecServlet?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> 
                           <input type="text" name="quantity" class="form-control w-50"  value="<%=c.getQuantity() %>" readonly>
								<a class="btn btn-sm btn-incre" href="QuantityincrementDecServlet?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
								<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="RemoveFromCartServlet?id=<%= c.getId()%>">Remove</a></td>
				</tr>
				<%}
				}%>


			</tbody>
		</table>
	</div>

	<%@include file="include/footer.jsp"%>
</body>
</html>