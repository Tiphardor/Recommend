<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>软件学院评选评优管理-用户登陆</title>
    <link rel="stylesheet" href="/Recommend/css/index.css"/>
    <script src="/Recommend/js/jquery-2.1.4.min.js" type="text/javascript"></script>
</head>
<body>
	<script type="text/javascript">
		function preSubmit(){
			var studentId = $("#studentId").val();
			if(studentId == null || studentId == ""){
				alert("用户名不能为空");
				return false;
			}
			var password = $("#password").val();
			if(password == null || password == ""){
				alert("密码不能为空");
				return false;
			}
		};
	</script>
	
	<c:if test = "${resultCode != null}" >
		<script type="text/javascript">
			alert("${resultCode}");
		</script>
	</c:if>

    <div class="login-body">
        <div class="login-content">
            <img src="/Recommend/img/logo.png" alt="" style="margin-bottom: 20px;">
            <form class="login-form" action="/Recommend/student/login.action" method="post">
                <p>学号 <input class="login-input" type="text" id="studentId" name="studentId" value="${studentId}"/></p>
                <p>密码 <input class="login-input" type="password" id="password" name="password" /></p>
                <p>类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="type" value="1" checked="checked" />学生 
					   &nbsp;&nbsp;&nbsp;<input type="radio" name="type" value="2" />教师 </p>
                <input class="login-btn" type="submit" value="登录" onclick="return preSubmit()"/>
            </form>
        </div>
    </div>
</body>
</html>