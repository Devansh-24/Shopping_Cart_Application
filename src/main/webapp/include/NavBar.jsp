
   
<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
User auth = (User) request.getSession().getAttribute("auth");

	request.setAttribute("auth", auth);

%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<div class="container">
  <a class="navbar-brand" href="index.jsp">E-Commerce Shopping Cart</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
 
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger px-1">${cart_list.size()}</span></a>
      </li>
     
     <% if(auth != null)
     {%>
    	 <li class="nav-item">
         <a class="nav-link disabled" href="order.jsp">Orders</a>
       </li>
       <li class="nav-item">
         <a class="nav-link disabled" href="logoutServlet">Logout</a>
       </li>
      
     <%}
     else
     { %>
    	 <li class="nav-item">
         <a class="nav-link disabled" href="login.jsp">Login</a>
         </li>
     <%}
     %>
    </ul>

  </div>
  </div>
</nav>
</body>
</html>