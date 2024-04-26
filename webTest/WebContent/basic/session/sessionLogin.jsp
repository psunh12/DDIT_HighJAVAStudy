<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	// JSP문서에서의 세션은 'session'이라는 변수에 이미 저장되어 있다.
	
	// 세션에 저장한 데이터 가져오기
	String userId = (String)session.getAttribute("loginUser");

%>


<body>
<%
if(userId==null){
%>
<form action="<%=request.getContextPath()%>/sessionLogin.do" method="post">
<table border="1" style="margin:0 auto;">
<tr>
	<td> ID : </td>
	<td> <input type="text" name="userid" placeholder="ID 입력">
	
</tr>
<tr>
	<td> PASS : </td>
	<td> <input type="password" name="pass" placeholder="PassWord 입력">
</tr>
<tr>
	<td colspan="2" style="text-align:center;">
		<input type="submit" value="Login">
	</td>
</tr>
</table>

</form>
<%
}else{
%>
<h3><%=userId %>님 반갑습니다.</h3><br>

<a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>
<%	
}
%>

</body>
</html>