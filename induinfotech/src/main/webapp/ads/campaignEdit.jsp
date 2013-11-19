<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head><title>Edit Campaign</title></head>
	
	<body>
		<h2>Edit Campaign - ${campaign.name}</h2>
		
		<c:if test="${message != ''}">
			<c:out value="${message}" />
		</c:if>
		
		<form name="campaignAddForm" action="campaign_edit_process.do" method="GET">
			<table border="1">
				<tr><td>Daily Budget in Dollars</td><td><input type="text" size="10" name="cDlyBudget" value="${campaign.dailyBudget}"/></td></tr>	
				<tr><td><input type="submit" value="Edit Campaign" /></td></tr>
			</table>
			
			<input type="hidden" name="cid" value="${campaign.id}" />
		</form>
	</body>
</html>