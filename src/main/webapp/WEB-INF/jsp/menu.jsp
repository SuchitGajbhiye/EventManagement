<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">
		<div>
			<a href="https://www.javaguides.net" class="navbar-brand">SS Events</a>
		</div>

		<ul class="navbar-nav navbar-collapse justify-content-start">
			<li><a href="<%= request.getContextPath() %>/new" class="nav-link">Create Event</a></li>
			<li><a href="<%= request.getContextPath() %>/searchEvent" class="nav-link">Search Event</a></li>
			<li><a href="<%= request.getContextPath() %>/pendingApprovals" class="nav-link">Pending Approvals</a></li>
			
		</ul>
		<ul class="navbar-nav navbar-collapse justify-content-end">
		<li class="nav-link">Welcome ${username}</li>
			<li><a href="<%= request.getContextPath() %>/login" class="nav-link">Logout</a></li>
			
		</ul>
	</nav>
</header>