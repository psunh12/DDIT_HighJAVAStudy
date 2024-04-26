<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	MemberVO memVo = (MemberVO)session.getAttribute("loginMember");
%>


<body>

<%
if(memVo==null){
%>
<form action="<%=request.getContextPath()%>/member/sessionLoginDb.do" method="post">
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
<h3><%=memVo.getMem_name() %>님 반갑습니다.</h3><br>

<a href="<%=request.getContextPath()%>/member/sessionLogoutDb.do">로그아웃</a>
<%
}
%>

</body>
</html>