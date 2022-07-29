<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">
		<div>
			<a href="https://www.javaguides.net" class="navbar-brand">SS Events</a>
		</div>

		<ul class="navbar-nav navbar-collapse justify-content-start">
		    <li><a href="<%= request.getContextPath() %>/eventsSummary" class="nav-link">Events Summary</a></li>
			<li><a href="<%= request.getContextPath() %>/pendingApprovals" class="nav-link">Pending Approvals</a></li>
			
		</ul>
		<ul class="navbar-nav navbar-collapse justify-content-end">
		<li class="nav-link">Welcome <%=request.getSession().getAttribute("user")%></li>
			<li><a href="<%= request.getContextPath() %>/logout" class="nav-link">Logout</a></li>
			
		</ul>
	</nav>
</header>