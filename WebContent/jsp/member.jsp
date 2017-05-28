<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_navigation.jsp"></jsp:include>
よこそう	${loginedUser.name }<br>
 ${errorString }

<br><a href="${pageContext.request.contextPath}/userList">User list</a>
<br><a href="${pageContext.request.contextPath}/changePassword">Change Password</a>
<jsp:include page="_footer.jsp"></jsp:include>