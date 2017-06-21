<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>



<script>
function init(){
	var btn = document.getElementById('btninput');
	btn.onclick = function(){
		location.href = 'inputView.do';
	}
}
	function del(id,pwd){
	var frm = document.frmResult;
	
	var returnValue = prompt("비밀번호를 입력해주세요.", "");
	if(returnValue==pwd){
		frm.userid.value = id;
		frm.userpwd.value = pwd;
		frm.action = 'delete.do';
		frm.submit();
	}else{
		alert("비밀번호가 틀렸습니다.");
		return;
	}
	
	}
	function mod(id,pwd){
		var ff = document.frmResult;
		ff.userid.value=id;
		ff.userpwd.value=pwd;
	    ff.action = "modify.do";
	    ff.submit();
	}
	
	
</script>
<style>
body{
text-align:center;
}
a{
text-decoration:none;
color:#000000;
}
a:hover{
background-color: #d4d4d4;
color: blue;
}
</style>
</head>
<body>
   <h2><spring:message code="list.title"></spring:message></h2>
   <br/><br/>
   <form name='frm' method='post' action='list.do'>
      <!-- input.do = spring mvc가 처리해 달라 -->
      <input type="button" id='btninput' value='회원신규등록'><p/>
      <input type='text' name="userid" /> <input type='submit' value='검색' />
  </form>

	<%-- <c:forEach var="item" items="${list}"><!--${list}는 리퀘스트 영역에 들어있는 list임  -->
	
    	<span><a href='#' onclick="id('${item.userid}')">아이디 : ${item.userid}</a></span>
     	<span><a href='#' onclick="id('${item.userid}')">비밀번호 : ${item.userpwd}</a></span><br/>
    
</c:forEach>
 --%>

<form name='frmResult'>
   <input type="hidden" name='userid'>
   <input type="hidden" name='userpwd'>
</form>

<c:forEach var='obj' items='${list}' >
   <span>유저아이디 : ${obj.userid }</span>
   <a href='#' onclick="del('${obj.userid }','${obj.userpwd }')">[삭제]</a>
  <a href='#' onclick="mod('${obj.userid }','${obj.userpwd }')">[수정]</a>
   <!-- <input type="button" value='수정' id='btnModify'> -->
   <br/>
 </c:forEach>




<script>init()</script>
</body>
</html>