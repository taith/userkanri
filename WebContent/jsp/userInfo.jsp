<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_navigation.jsp"></jsp:include>
 ${userInfo.name }<br>
 ${userInfo.created_at }
  ${errorString }
 <a href="${pageContext.request.contextPath}/delete/${userInfo.id}">削除</a>
 <a href="">情報変更</a>
<jsp:include page="_footer.jsp"></jsp:include>