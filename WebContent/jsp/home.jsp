<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<input type="checkbox" id="pass" name="pass" value="123456">123456
<input type="checkbox" id="pass" name="pass" value="1234567">1234567
<div id="kekka"></div>
<jsp:include page="_footer.jsp"></jsp:include>
<script>
	
	$(document).ready(function() {
		$('#role-select').change(function(event) {
			var id = $('#id-select').val();
			var role = $('#role-select').val();
			$.get('ajax', {
				id : id,
				role : role
			}, function(responseText) {
				$('#kekka').text(responseText);
			});
		});
		
		$('#pass').click(function(event) {
			var id = $('#id-select').val();
			var role = $('#role-select').val();
			$.get('ajax', {
				id : id,
				role : role
			}, function(responseText) {
				$('#kekka').text(responseText);
			});
		});
		
		$('#id-select').change(function(event) {
			var id = $('#id-select').val();
			var role = $('#role-select').val();
			$.get('ajax', {
				id : id,
				role : role
			}, function(responseText) {
				$('#kekka').text(responseText);
			});
		});
		
	});
</script>