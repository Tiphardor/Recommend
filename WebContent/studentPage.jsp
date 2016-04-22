<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>软件学院评选评优管理-学生页面</title>
    <link rel="stylesheet" href="/Recommend/css/index.css"/>
    <link rel="stylesheet" href="/Recommend/css/animate.css">
</head>
<body>
    <header>
        <h1>软件学院评选评优管理</h1>
        <a class="logout" href="/Recommend/student/logout.action?studentId=${sdv.studentId}">注&nbsp;&nbsp;销</a>
        <a class="updatepass" href="/Recommend/updatePassPage.jsp?studentId=${sdv.studentId}">修改密码</a>
    </header>
    <div class="page-body">
        <div class="person-message">
            <div class="person-detail">
                <div>
                    <p>学号：<span class="number">${sdv.studentId}</span></p>
                    <p>姓名：<span class="name">${sdv.studentName}</span></p>
                    <p>方向：<span class="major">${sdv.major}</span></p>
                </div>
            </div>
        </div>
        <div class="course public-course">
            <div class="course-title">专业学位课</div>
            <div class="result">
                <div>
                	<c:if test="${sdv.degree != null}">
                		<c:forEach var="degree" items="${sdv.degree}" varStatus="status">   
     						<div class="col-3 course-detail">
                        	  <div class="product_section1">
                            	<div class="course-name">课程：${degree.courseName}</div>
                            	<div class="course-score">分数：${degree.courseGrade}</div>
                        	  </div>
                        	  <div class="product_section2">
                            	<div class="course-credit">学分：${degree.courseCredit}</div>
                        	  </div>
                            </div>
						</c:forEach>          		
               	 	</c:if>
               	</div>
           </div>
        </div>
        <div class="course major-courser">
            <div class="course-title">专业方向必修课</div>
            <div class="result">
                <div>
                  <c:if test="${sdv.compulsory != null}">
                		<c:forEach var="compulsory" items="${sdv.compulsory}" varStatus="status">   
     						<div class="col-3 course-detail">
                        	  <div class="product_section1">
                            	<div class="course-name">课程：${compulsory.courseName}</div>
                            	<div class="course-score">分数：${compulsory.courseGrade}</div>
                        	  </div>
                        	  <div class="product_section2">
                            	<div class="course-credit">学分：${compulsory.courseCredit}</div>
                        	  </div>
                            </div>
						</c:forEach>          		
               	 	</c:if>
                </div>
            </div>
        </div>
        <div class="final-result">
            <span>总加权分：</span><span class="final-average-score">${aveGrade.averageGrade}</span>&nbsp;&nbsp;
            <span>班级排名：</span><span class="final-class-num">${aveGrade.rate}</span>&nbsp;&nbsp;
        </div>
    </div>
    <script src="/Recommend/js/jquery-2.1.4.min.js"></script>
    <script src="/Recommend/js/studentPage.js"></script>
</body>
</html>