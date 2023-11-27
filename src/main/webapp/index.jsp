<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Dao.ProductDao"%>
<%@page import="Connection.DbCon"%>
<%@page import="Model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");

if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd=new ProductDao(DbCon.getconnection());
List<Product> products=pd.getAllProduct();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	/* ProductDao pdao = new ProductDao(DbCon.getconnection());
	cartProduct = pdao.getCartProduct(cart_list); */
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome TO Shoping Cart</title>
<%@include file="include/head.jsp"%>
</head>
<body>

	<%@include file="include/NavBar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
		<%
		   if(!products.isEmpty())
		   {
			   for(Product p: products)
			   {%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" src="product-image/<%= p.getImage() %>" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%= p.getName() %></h5>
						<h6 class="price">Price:<%= p.getPrice()%> $</h6>
						<h6 class="category">Category : $<%= p.getCategory() %></h6>
						<div class="mt-3-justify-content-between">
						<a href="AddtoCart?id=<%= p.getId() %>" class="btn btn-dark">Add to Cart</a>
						<a href="BuyNowServlet?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
						</div>
						
					</div>
				</div>
			</div>
			   <%}
		   }
		%>
		</div>
	</div>

	<%@include file="include/footer.jsp"%>
</body>
</html>