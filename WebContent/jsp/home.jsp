<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_navigation.jsp"></jsp:include>

ID
<select id="id-select" class="selectpicker">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
</select>

Role
<select id="role-select" class="selectpicker">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
</select>
Pass
<input type="checkbox" id="pass" name="pass" value="123456">
123456
<input type="checkbox" id="pass" name="pass" value="1234567">
1234567
<div id="kekka"></div>
<c:if test="${userid.email != null }">
	${userObj.email}
</c:if>
<jsp:include page="_footer.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		var $mail;
		$('#id-select').change(function(event) {
			var $id = $("select#id-select").val();
			var $role = $("select#role-select").val();
			$.get('ajax', {
				id : $id,
				role: $role
			}, function(responseJson) {
				$mail = responseJson.email;
				$mail = $mail + "a"
				$('#kekka').text($mail);
			});
		});
	});
</script>