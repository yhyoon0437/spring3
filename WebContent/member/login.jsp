<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인</h2>
	<form name='frm' method='post' action='loginR.do'>
		<label>회원 아이디</label> 
		<input type='text' name='userid' value="">
		<span class='error'>${userid}</span>
		<!--userid는 필드명이 아니라 에러 코드에 키값에 해당한다.  --><br /> 
		
		<label>회원 암호</label> 
		<input type='password' name='userpwd' value='' /> 
		<span class='error'>${userpwd}</span>
		<!--usepwd는 필드명이 아니라 에러 코드에 키값에 해당한다.  --><p />
		<span><strong>${msg }</strong></span>
		
		
		<input type='submit' value='로그인하기' />
	</form>
</body>
</html>