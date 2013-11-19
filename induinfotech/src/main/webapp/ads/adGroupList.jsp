<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head><title>Adgroups for ${campaign.id}</title></head> <a href="adgroup_add.do?cid=${campaign.id}">Add AdGroup</a> <br />
	
	<body>
		<c:if test="${message != ''}">
			<c:out value="${message}" />
		</c:if>
		
		<h2>[${campaign.id}] - AdGroup List</h2>
		
		<c:if test="${empty campaign.adGroups}">
			<br />Currently Campaign:${campaign.id} has no AdGroups. Please add by clicking <a href="adgroup_add.do?cid=${campaign.id}">here</a>
		</c:if>

		<c:if test="${! empty campaign.adGroups}">
			<table border="1">
				<tr><td>ID</td><td>Name</td><td>Daily Budget</td><td>Actions</tr>
				
				
					<c:forEach items="${campaign.adGroups}" var="adGroup" varStatus="status">
						<tr>
							<td><a href="adgroup_home.do?cid=${campaign.id}&aid=${adGroup.id}"><c:out value="${adGroup.id}" /></a></td>
							<td><c:out value="${adGroup.name}" /></td>
							<td><c:out value="${adGroup.dailyBudget}" /></td>					
							<td><a href="adgroup_edit.do?cid=${campaign.id}&aid=${adGroup.id}">Edit</a> &nbsp;&nbsp;&nbsp;</td>
						</tr>
					</c:forEach>	
			</table>
		</c:if>		


	</body>
</html>