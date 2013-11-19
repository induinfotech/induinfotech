<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head><title>Campaign List</title></head> <a href="campaign_add.do">Add Campaign</a> <br />
	
	<body>
		<c:if test="${message != ''}">
			<c:out value="${message}" />
		</c:if>
		
		<h2>Campaign List</h2>
		
		<table border="1">
			<tr><td>ID</td><td>Name</td><td>Start Date</td><td>End Date</td><td>Daily Budget</td><td>Actions</tr>
			
			<c:forEach items="${campaigns}" var="campaign" varStatus="status">
				<tr>
					<td><a href="adgroup_list.do?cid=${campaign.id}"><c:out value="${campaign.id}" /></a></td>
					<td><c:out value="${campaign.name}" /></td>
					<td><fmt:formatDate pattern="MM-dd-yyyy" value="${campaign.startDt}" /></td>
					<td><fmt:formatDate pattern="MM-dd-yyyy" value="${campaign.endDt}" /></td>
					<td><c:out value="${campaign.dailyBudget}" /></td>					
					<td><a href="campaign_edit.do?cid=${campaign.id}">Edit</a><a href="campaign_expire.do?cid=${campaign.id}">Expire</a></td>
				</tr>
			</c:forEach>			
		</table>
	</body>
</html>