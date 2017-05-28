<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_navigation.jsp"></jsp:include>
<c:forEach items="${userList}" var="user">
	<br><a href="${pageContext.request.contextPath}/userInfo/${user.id}">${user.email }</a><br>
	${user.name }<br>
</c:forEach>
<jsp:include page="_footer.jsp"></jsp:include>