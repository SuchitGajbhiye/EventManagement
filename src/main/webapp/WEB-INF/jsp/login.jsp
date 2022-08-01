<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" http-equiv="Content-Type" content="text/html">
<title>Event Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<script type="text/javascript">
	<%
	//save just user name in session
	request.getSession().setAttribute("username", request.getParameter  ("username"));
	 %>
	</script>
	<div class="container col-md-5" style="overflow: auto;padding-top: 15px;">
	<div class="card">
			<div class="card-body">
		<h1 style="text-align: left;font-size: 20px;">Login Form</h1>
		<%-- <form action="<%=request.getContextPath()%>/login" method="post"> --%>
		<form method="post" action="login">
			<div class="form-group" style="width:50%">
				<label for="uname">User Name:</label> <input type="text"
					class="form-control" id="username" placeholder="User Name"
					name="username" required>
			</div>
			
			<div class="form-group" style="width:50%">
				<label for="uname">Password:</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password" required>
			</div>
			<input type="radio" name="role" value="admin"/>Admin
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="role" value="student" checked/>Student
			</br>
			</br>

		<div>
		<button type="submit" class="btn btn-primary">Submit</button>
		<span style="color: red">${errorMessage}</span>
		</div>
			
		</form>
	</div>
	</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script type="text/javascript">
	
	function validate() {  
		alert("here");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		alert(username);
		alert(password);
		if(username.equals("Siddhesh") && password.equals("Siddhesh")){
			//if the user is valid, then this block executes and WE ARE KEEPING THE USER IN A SESSION
			session.setAttribute("user", username);//THIS IS HOW WE DECLARE THE USER IN A SESSION
			response.sendRedirect("eventsList.jsp"); //AND WE REDIRECTED TO LOGIN PAGE
			alert(session.getAttribute("user").value);
		}else{
			
			//IF THE USER IS NOT AUTHORISED THEN AGAIN HE WILL BE REDIRECTED TO THE SAME LOGIN PAGE
			response.sendRedirect("login.jsp");
		}
	}
	</script>
</body>
</html>