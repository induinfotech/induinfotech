<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head><title>Edit AdGroup</title></head>
	
	<body>
		<h2>Edit AdGroup</h2>
		
		<c:if test="${message != ''}">
			<c:out value="${message}" />
		</c:if>
		
		<form name="adGroupEditForm" action="adgroup_edit_process.do" method="GET">
			<table border="1">
				<tr><td>Campaign Id</td><td>${cid} <input type="hidden" name="cid" value="${cid}" /></td></tr>
				<tr><td>AdGroup Id</td><td>${aid} <input type="hidden" name="aid" value="${adGroup.id}" /></td></tr>
				<tr><td>Name</td><td><input type="text" size="10" name="aName" value="${adGroup.name}" /></td></tr>
				<tr><td>Daily Budget</td><td><input type="text" size="10" name="aDlyBudget" value="${adGroup.dailyBudget}"/> USD</td></tr>	
				<tr><td>Keywords</td><td><textarea name="keywords" value=""></textarea></td></tr>
				<tr><td><input type="submit" value="Edit AdGroup" /></td></tr>
				
				<input type="hidden" name="currency" value="USD" />
			</table>
		</form>
	</body>
</html>