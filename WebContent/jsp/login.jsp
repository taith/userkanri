<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_navigation.jsp"></jsp:include>
<div class="container">
	<h3>Login</h3>
   	<form class="form-horizontal" name="loginForm" method="POST" action="doLogin">
	    <div class="form-group">
	        <label for="username" class="col-md-3 control-label">メール*</label>
	        <div class="col-md-9">
	            <input type="email" class="form-control" id="email" name="email"
	                placeholder="Email" required="required">
	        </div>
	    </div>
	    <br>
	    <br>
	    <div class="form-group">
	        <label for="password" class="col-md-3 control-label">パスワード*</label>
	        <div class="col-md-9">
	            <input type="password" class="form-control" id="password" name="password"
	                placeholder="Password" required="required" pattern=".{6,16}"> 
	        </div>
	    </div>
	    <br>
	    <br>
	    <div class="form-group">
	        <div class="col-sm-offset-3 col-md-9">
	            <button type="submit" class="btn btn-primary btn-block">ログイン</button>
	        </div>
	    </div>
	</form>
</div>

<jsp:include page="_footer.jsp"></jsp:include>