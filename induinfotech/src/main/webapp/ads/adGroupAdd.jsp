<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head><title>Add AdGroup</title></head>
	
	<body>
		<h2>Add AdGroup</h2>
		
		<c:if test="${message != ''}">
			<c:out value="${message}" />
		</c:if>
		
		<form name="adGroupAddForm" action="adgroup_add_process.do" method="GET">
			<table border="1">
				<tr><td>Campaign</td><td>${cid} <input type="hidden" name="cid" value="${cid}" /></td></tr>
				<tr><td>Name</td><td><input type="text" size="10" name="aName" /></td></tr>
				<tr><td>Daily Budget</td><td><input type="text" size="10" name="aDlyBudget" /> USD</td></tr>	
				<tr><td>Keywords</td>
					<td>
						<textarea name="keywords" value=""></textarea>
					</td>
				</tr>
				<tr><td><input type="submit" value="Add AdGroup" /></td></tr>
				
				<input type="hidden" name="currency" value="USD" />
			</table>
		</form>
	</body>
</html>