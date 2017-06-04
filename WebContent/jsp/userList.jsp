<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_navigation.jsp"></jsp:include>
<c:forEach items="${userList}" var="user">
	<br>
	<a href="${pageContext.request.contextPath}/userInfo/${user.id}">${user.email }</a>
	<br>
	${user.name }<br>
</c:forEach>
<table border="1" id="tblSample">
	<tr>
		<th>#</th>
		<th>Number</th>
		<th>ID</th>
		<th>Name</th>
		<th>Email</th>
	</tr>
	<tr>
		<td><input type="button" value="Add" onclick="addRowToTable();" /></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
<jsp:include page="_footer.jsp"></jsp:include>
<script>
	function addRowToTable() {
		var tbl = document.getElementById('tblSample');
		var lastRow = tbl.rows.length;
		// if there's no header row in the table, then iteration = lastRow + 1
		var iteration = lastRow;
		var row = tbl.insertRow(1);

		// left cell
		var cellLeft = row.insertCell(0);
		var removeButton = document.createElement('input');
		removeButton.setAttribute('type','button');
		removeButton.setAttribute('value','Remove');
		removeButton.setAttribute('onclick','removeRowFromTable()');
		cellLeft.appendChild(removeButton);

		// right cell
		var cellRight = row.insertCell(1);
		var el = document.createElement('input');
		el.type = 'number';
		el.name = 'choiceNumberRow' + iteration;
		el.id = 'choiceNumberRow' + iteration;

		cellRight.appendChild(el);

		// select id
		var cellRightSel = row.insertCell(2);
		var sel = document.createElement('select');
		sel.name = 'selIDRow' + iteration;
		<c:forEach items="${userList}" var="users">
		var opt = document.createElement('option');
		opt.value = "${users.id}";
		opt.text = "${users.id}";
		sel.options.add(opt);
		</c:forEach>

		cellRightSel.appendChild(sel);

		// select name
		var cellRightSel1 = row.insertCell(3);
		var sel1 = document.createElement('select');
		sel1.name = 'selNameRow' + iteration;
		<c:forEach items="${userList}" var="users">
		var opt1 = document.createElement('option');
		opt1.value = "${users.name}";
		opt1.text = "${users.name}";
		sel1.options.add(opt1);
		</c:forEach>

		cellRightSel1.appendChild(sel1);
		
		// select email
		var cellRightSel2 = row.insertCell(4);
		var sel2 = document.createElement('select');
		sel2.name = 'selEmailRow' + iteration;
		<c:forEach items="${userList}" var="users">
		var opt2 = document.createElement('option');
		opt2.value = "${users.email}";
		opt2.text = "${users.email}";
		sel2.options.add(opt2);
		</c:forEach>

		cellRightSel2.appendChild(sel2);
	}
	function removeRowFromTable()
	{
	  var tbl = document.getElementById('tblSample');
	  var lastRow = tbl.rows.length;
	  if (lastRow > 2) tbl.deleteRow(lastRow-2);
	}
</script>