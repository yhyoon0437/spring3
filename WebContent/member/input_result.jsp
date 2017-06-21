<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 결과</title>
<script>
function init(){
	var btn = document.getElementById('btnlist');
	btn.onclick = function(){
		location.href = 'list.do';
	}
}
</script>
</head>
<body>
<h2><spring:message code="inputResult.title"/></h2>
${msg }<br/><br/>
<form:form action="list.do" commandName="MemberVo">
 <p>
 <label>이메일 : </label>
 <form:input path="email"/>
 <form:errors path="email"/>
 </p>
 <p>
 <label>userID : </label>
 <form:input path="userid"/>
 <form:errors path="userid"/>
 </p>
 <p><label>비밀번호 : </label>
 <form:input path="userpwd"/>
 <form:errors path="userpwd"/>
 </p>
 <p><label>비밀번호 확인 : </label>
 <form:input path="confirmpwd"/>
 <form:errors path="confirmpwd"/>
 </p>
 
 
<input type='submit' value='목록으로'/> 
 </form:form>


<script>init()</script>
</body>
</html>