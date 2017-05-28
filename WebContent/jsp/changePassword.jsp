<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_navigation.jsp"></jsp:include>
 ${loginedUser.id }
 ${errorString}
	<div class="container">
	<h3>Login</h3>
   	<form class="form-horizontal" name="changePass" method="POST" action="doChangePassword">
	    <div class="form-group">
	        <label for="password" class="col-md-3 control-label">現在のパスワード*</label>
	        <div class="col-md-9">
	            <input type="password" class="form-control" id="password" name="password"
	                placeholder="現在のパスワード" required="required" pattern=".{6,16}">
	        </div>
	    </div>
	    <br>
	    <div class="form-group">
	        <label for="new-password" class="col-md-3 control-label">新しいパスワード*</label>
	        <div class="col-md-9">
	            <input type="password" class="form-control" id="new-password" name="new-password"
	                placeholder="新しいパスワード" required="required" pattern=".{6,16}"> 
	        </div>
	    </div>
	    <div class="form-group">
	        <label for="confirm-password" class="col-md-3 control-label">新しいパスワード*</label>
	        <div class="col-md-9">
	            <input type="password" class="form-control" id="confirm-password" name="confirm-password"
	                placeholder="パスワードの確認" required="required" pattern=".{6,16}"> 
	        </div>
	    </div>
	    <br>
	    <div class="form-group">
	        <div class="col-sm-offset-3 col-md-9">
	            <button type="submit" class="btn btn-primary btn-block">変更</button>
	        </div>
	    </div>
	</form>
</div>
<jsp:include page="_footer.jsp"></jsp:include>