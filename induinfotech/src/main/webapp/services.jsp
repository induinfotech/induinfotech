<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
	<TITLE>Welcome to Indu Infotech</TITLE>
	<link rel="stylesheet" type="text/css" href="css/main.css" />
	<link rel="stylesheet" type="text/css" href="css/calendar.css" />
		
	<SCRIPT SRC="scripts/basiccalendar.js"></SCRIPT>
</HEAD>

<body scroll="auto">
	<jsp:include page="header.jsp" />
			
	<div id="contentDiv">
		<div id="mainContent">
			<a href="index.jsp">Home</a> >>
			<a href="services.jsp">Services</a>

			<P>At PortalWorld, we are experienced in helping customers in evaluating,
			architecting, installation and support of appropriate portal
			framework based on their needs</P>

			<P>We specialize in following portal frameworks
				<LI>BEA	Aqualogic User Interaction (Formerly Plumtree Corporate Portal)</LI>
				<LI>BEA Weblogic Portal</LI>
				<LI>TIBCO Portal Builder</LI>
				<LI>JBoss</LI>
			</P>

			<P>Our specialist consultant can help you configure following supporting
			   technologies to leverage the power of portals in your organization

				<LI>Collaboration</LI>
				<LI>Content Management</LI>
				<LI>Workflow process</LI>
			</P>

		</div>
			
		<div class="rightRail" align="right">
		<SCRIPT>
			var todaydate=new Date()
			var curmonth=todaydate.getMonth()+1 //get current month (1-12)
			var curyear=todaydate.getFullYear() //get current year

			document.write(buildCal(curmonth ,curyear, "main", "month", "daysofweek", "days", 1));

		</SCRIPT>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

</BODY>

</HTML>