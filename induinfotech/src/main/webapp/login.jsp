<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/main.css">
			
		<title>Welcome to Indu Infotech - Login</title>
	</head>

	<body onload='document.f.j_username.focus();' scroll="auto">
		<jsp:include page="header.jsp" />
		<div id="contentDiv">
		
			<div id="mainContent">
			
				<a href="login.do">Login</a><br>

					<h3>Welcome to Indu Infotech Inc. Please login using your username/password</h3>
				 
					<c:if test="${not empty error}">
						<div class="errorblock">
							Your login attempt was not successful, try again.<br /> Caused :
							${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
						</div>
					</c:if>
				 
					<form name='f' action="<c:url value='j_spring_security_check' />"
						method='POST'>
				 
						<table>
							<tr>
								<td>User:</td>
								<td><input type='text' name='j_username' value=''>
								</td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input type='password' name='j_password' />
								</td>
							</tr>
							<tr>
								<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
								<td colspan='2'><input name="reset" type="reset" />	</td>
							</tr>
						</table>
				 
					</form>
			</div>
		</div>

		<jsp:include page="footer.jsp" />

	</body>
</html>						