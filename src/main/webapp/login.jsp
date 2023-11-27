<%@page import="java.util.*"%>
<%@page import="Model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	  <%
    User auth = (User)request.getSession().getAttribute("auth");
    
    if(auth != null)
    {
    	/* request.setAttribute("auth", auth); */
    	response.sendRedirect("index.jsp");
    	
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
   
    	request.setAttribute("cart_list", cart_list);
    }
    %>

   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shoping Cart Login</title>
<%@include file="include/head.jsp"%>
</head>
<body>
<%@include file="include/NavBar.jsp" %>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="Post">

					<div class="form-group">
						<lable>Email Address</lable>
						<input type="email" class="form-control" name="login-email"
							placeholder="Enter your Email" required="required"></input>
					</div>
					<div class="form-group">
						<lable>Password</lable>
						<input type="Password" class="form-control" name="login-password"
							placeholder="**********" required="required"></input>
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>

	</div>

	<%@include file="include/footer.jsp"%>
</body>
</html>