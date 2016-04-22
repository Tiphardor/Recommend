<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>软件学院评选评优管理-教师页面</title>
    <link rel="stylesheet" href="/Recommend/css/index.css"/>
    <link rel="stylesheet" href="/Recommend/css/animate.css">
    <script src="/Recommend/js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script src="/Recommend/js/ajaxfileupload.js" type="text/javascript"></script>
  
</head>
<body>
<script type="text/javascript">

$(document).ready(function(){
	$("#modal").addClass("hidden");
});

function uploadFileAjax(){
	//判断是否有选择上传文件
    var imgPath = $("#uploadFile").val();
    if (imgPath == "") {
        alert("请选择上传文件！");
        return false;
    }
    //判断上传文件的后缀名
    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    if (strExtension != 'xls' && strExtension != 'xlsx') {
        alert("请选择excel文件");
        return false;
    }
    
    $.ajaxFileUpload({
    	url: '/Recommend/file/upload.action', //用于文件上传的服务器端请求地址
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: 'uploadFile', //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        success: function (data,status){
        	alert("成功");
        	alert(data);
        },
        error: function (data, status, e){
        	alert("失败");
        }
	});
}


function exportExcel(){

	var exportMajor = $("#exportMajor").val();
		
	var form=$("<form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("method","post");
	form.attr("action","/Recommend/student/exportExcelFile.action");
	var input1=$("<input>");
	input1.attr("type","hidden");
	input1.attr("name","major");
	input1.attr("value",exportMajor);
	$("body").append(form);//将表单放置在web中
	form.append(input1);

	form.submit();//表单提交 
	
}

	

	function uploadFileSubmit() {
    	//判断是否有选择上传文件
        var imgPath = $("#uploadFile").val();
        if (imgPath == "") {
            alert("请选择上传文件！");
            return false;
        }
        //判断上传文件的后缀名
        var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
        if (strExtension != 'xls' && strExtension != 'xlsx') {
            alert("请选择excel文件");
            return false;
        }
    	$("#modal").removeClass("hidden");
    }
    
    function findInfo(){
    	var major = $("#major").val();
    	if(major == "请选择..."){
    		alert("请选择方向");
    		return false;
    	}
    	var type = $("#type").val();
    	if(type == "请选择..."){
    		alert("请选择排序方式");
    		return false;
    	}
    	
    	$.ajax({
    	    url: "/Recommend/student/findMajorStudent.action",    //请求的url地址
    	    dataType: "json",   //返回格式为json
    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    	    data: { "major": major,"type":type },    //参数值
    	    type: "post",   //请求方式
    	    success: function(data) {
    	    	if(!jQuery.isEmptyObject(data)){
    	    		$("#table").empty();
    	    		$("#table").append("<input type='hidden' id='exportMajor' value="+major+"></input>");
    	    		$("#table").append("<h3><span class='table-title'>"+major+"</span>加权学分信息表</h3>");
    	    		$("#table").append("<table id='table2'><tr ><th>学号</th><th>姓名</th><th>加权学分</th><th>排名</th><th>查看详细</th></tr></table>");
    	    		$.each(data,function(number,value) {
    	    			$("#table2").append("<tr><td>"+value.studentId+"</td><td>"+value.studentName+"</td><td>"+value.averageGrade+"</td><td>"+value.rate+"</td><td><a href='/Recommend/student/findDetailStudent.action?studentId="+value.studentId+"'>查看详细</a></td></tr>");
    	    		});
    	    		/* $("#table").append("</table></div>"); */
    	    		$("#table").append("<div style='text-align: right;width: 80%;margin: 0 auto;border-bottom: 1px solid black;'><input type='button' value='导出' onclick='return exportExcel()' class='outport-btn' /></div>");
    	        	//请求成功时处理
    	    	}else{
    	    		$("#table").empty();
    	    		$("#table").append("<h3><span class='table-title'>"+major+"</span>加权学分信息表</h3></br><span class='table-title'>暂无</span>");
    	    	}
    	    },
    	    error: function() {
    	    	alert("异常");
    	        //请求出错处理
    	    }
    	});
    }

</script>

<header>
    <h1>软件学院评选评优管理</h1>
    <a class="logout" href="/Recommend/student/logout.action?studentId=${sdv.studentId}">注&nbsp;&nbsp;销</a>
</header>

<c:if test="${resultCode != null}">
	<script type="text/javascript">
		$("#modal").addClass("hidden");
		alert("${resultCode}");
	</script>
</c:if>

<div id="modal" class="hidden">
    <div id="myModal"></div>
    <div class="modalDialog">
        <div class="modal-body">
            <h4>文件正在上传,请耐心等待......</h4>
        </div>
    </div>
</div>

<div class="page-body" style="text-align: center;">
    <div class="import">
    <form action="/Recommend/file/upload.action" method="post" enctype="multipart/form-data">
	<input type="file" id="uploadFile" name="uploadFile">
  	<input type="submit" id="submitButton"  class='import-btn' value="导入" onclick="return uploadFileSubmit()"/>
  	<div class="import-result">
    	<span class="import-success unvisible">文件导入成功</span>
        <span class="import-error unvisible">文件导入失败</span>
    </div>
    </form>
    </div>
    <div class="student-table">
        <div class="select-content">
            <div class="select-box">
                <span>选择方向</span>
                <input type="text" value="请选择..." id="major" class="select-input" readonly="readonly">
                <ul class="select-ul">
                    <li>移动互联网与游戏开发技术</li>
                    <li>软件开发技术</li>
                    <li>物联网开发技术</li>
                    <li>云计算开发技术</li>
                    <li>信息产品设计</li>
                    <li>大数据开发与应用技术</li>
                    <li>金融数据分析技术</li>
                </ul>
            </div>
            <div class="select-box">
                <span>排序方式</span>
                <input type="text" value="请选择..." id="type" class="select-input" readonly="readonly">
                <ul class="select-ul">
                    <li>学号</li>
                    <li>名次</li>
                </ul>
            </div>
           <div style="display:inline-block;">
            	<input class="select-btn" type="button" value="查询" onclick="return findInfo()"/>
            </div>
        </div>
        <div class="table" id="table">
           <c:if test="${aveGradeList != null}">
        	<h3><span class='table-title'>${majorName}</span>加权学分信息表</h3>
        	<input type='hidden' id='exportMajor' value="${majorName}"></input>
        	<div>
        		<table class="table-body">
        			 <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>加权学分</th>
                        <th>排名</th>
                        <th>查看详细</th>
                    </tr>
            <c:forEach items="${aveGradeList}" var="aveGrade">
                <tr>
                    <td>${aveGrade.studentId}</td>
                    <td>${aveGrade.studentName}</td>
                    <td>${aveGrade.averageGrade}</td>
                    <td>${aveGrade.rate}</td>
                    <td>
                       <a href="/Recommend/student/findDetailStudent.action?studentId=${aveGrade.studentId}">查看详细</a>
                    </td>
                </tr>
            </c:forEach>
            </table>
        	</div>
        	<div style='text-align: right;width: 80%;margin: 0 auto;border-bottom: 1px solid black;'><input type='button' value='导出' onclick='return exportExcel()' class='outport-btn' /></div>
        	</c:if>
        </div>
        
        <div class="illustration">
            <h3>说明：</h3>
            <div class="illustration-detail">本表计划规则
            	<ul>
            		<li>上传文件必须是.xls或者.xlsx结尾的不带有文本性质的excel文件</li>
            		<li>文件中字段不要有多余的空格</li>
            		<li>列依次为学号、姓名、专业、课程1、课程2、……</li>
            		<li>课程列顺序：课程编号、课程名、学分</li>
            	</ul>
            	<img src="/Recommend/img/show.jpg" width="500dp" height="90dp" />
            </div>
        </div>
    </div>
</div>
<script src="/Recommend/js/teacherPage.js"></script>
</body>
</html>