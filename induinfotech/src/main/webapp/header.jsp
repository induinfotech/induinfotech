 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="headerDiv">
   <div id="logo"><img src="img/InduLogo.jpg" alt="Indu Infotech Inc" /></div>
   <div id="nav">
     <a class="sidelink" href="index.jsp">Home</a>&nbsp;&nbsp;
   	 <a class="sidelink" href="services.jsp">Services</a>&nbsp;&nbsp;
   	 <a class="sidelink" href="clients.jsp">Clients</a>&nbsp;&nbsp;
   	 <a class="sidelink" href="contactus.jsp">Contact Us</a>&nbsp;&nbsp;
   	 
   	 <a class="sidelink" href="brochure/index.html">Brochure</a>&nbsp;&nbsp;
   	
	<sec:authorize access="isAuthenticated()">
		Welcome ${username} [<sec:authentication property="principal.authorities"/>]&nbsp;&nbsp;	
		
		<sec:authorize access="hasAnyRole('admin', 'advertiser')">
			<a class="sidelink" href="adv_portal.do">Advertisers</a>
		</sec:authorize>
		
		<a class="sidelink" href="<c:url value="/j_spring_security_logout" />" >Logout</a>
	</sec:authorize>

	<sec:authorize access="isAnonymous()">
		Welcome guest&nbsp;&nbsp;
		<a class="sidelink" href="<c:url value="login.do" />" > Login</a>
	</sec:authorize>
   </div>
 </div>
