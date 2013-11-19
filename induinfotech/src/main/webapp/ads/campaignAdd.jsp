<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head><title>Add Campaign</title></head>
	
	<body>
		<h2>Add Campaign</h2>
		
		<c:if test="${message != ''}">
			<c:out value="${message}" />
		</c:if>
		
		<form name="campaignAddForm" action="campaign_add_process.do" method="GET">
			<table border="1">
				<tr><td>Name</td><td><input type="text" size="10" name="cName" /></td></tr>
				<tr><td>Start Date (mm/dd/yyyy)</td><td><input type="text" size="10" name="cStartDt" /></td></tr>
				<tr><td>End Date (mm/dd/yyyy)</td><td><input type="text" size="10" name="cEndDt" /></td></tr>		
				<tr><td>Daily Budget in Dollars</td><td><input type="text" size="10" name="cDlyBudget" /></td></tr>	
				<tr><td><input type="submit" value="Add Campaign" /></td></tr>
				
				<input type="hidden" name="currency" value="USD" />
			</table>
		</form>
	</body>
</html>