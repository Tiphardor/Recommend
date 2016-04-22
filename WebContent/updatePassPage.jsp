<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/Recommend/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<title>修改密码</title>
</head>
<body>
<script type="text/javascript">
	function check(){
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var rePassword = $("#rePassword").val();
		if(oldPassword == ""){
			alert("请输入原密码");
			return false;
		}
		if(newPassword == ""){
			alert("请输入新密码");
			return false;
		}
		if(newPassword != rePassword){
			alert("两次密码不一致");
			return false;
		}
		return true;
	}
</script>
<c:if test="${resultCode != null}">
	<script type="text/javascript">
		alert("${resultCode}");
	</script>
</c:if>
<form action="/Recommend/student/updateStudentPassword.action" method="post">
	<input type="hidden" value="${studentId}" name="studentId" />
	原密码：<input type="password" name="oldPassword" id="oldPassword"/><br />
	新密码：<input type="password" name="newPassword" id="newPassword"/><br />
	重复密码：<input type="password" name="rePassword" id="rePassword"/><br />
	<input type="submit" value="修改密码" onclick="return check()"/>
</form>
</body>
</html>