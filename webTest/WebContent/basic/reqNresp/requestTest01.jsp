<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!--
	HTML 주석 
-->
<%--
	이것은 JSP 주석을 나타낸다.
--%>
<%
	// 이 영역은 JSP문서에서 JAVA명령을 사용할 수 있는 영역으로 '스크립트릿'이라고 한다.
	String name = "홍길동";
%>

<%--
	<%=변수 나 수식 %>   ==> JSP에서 변수의 값이나 수식의 결과를 출력할 때 사용한다.
							(이것은 '표현식(Expression)'이라고 한다.)
--%>

<!-- 
	<form>태그의 속성
1) action ==> form에서 구성한 데이터를 받아서 처리할 문서명 또는 서블릿URL
			  ( 생략하면 현재 문서가 기본값이 된다. )
2) method ==> 전송방식 (GET 또는 POST) ==> 기본값 : GET
3) target ==> 응답이 나타날 프레임을 지정한다.
4) enctype ==> 서버로 파일을 전송할 때 이 속성을 'multipart/form-data'로 지정한다.

-->
<body>
<h2><%=name %> Request연습용 Form <%=300 - 20 * 2 %></h2>
<form action="/webTest/requestTest01.do" method="post">

<table border="1">
<tr>
	<td>이 름</td>
	<td><input type="text" size="10" name="username"></td>
</tr>

<tr>
	<td>직 업</td>
	<td>
		<select name="job">
			<option value="회사원">=회사원=</option>
			<option value="전문직">=전문직=</option>
			<option value="학생">=학 생=</option>
			<option value="무직">=무 직=</option>
		</select>
	</td>
</tr>

<tr>
	<td>취 미</td>
	<td>
		<input type="checkbox" name="hobby" value="여행">여행
		<input type="checkbox" name="hobby" value="독서">독서
		<input type="checkbox" name="hobby" value="게임">게임
		<input type="checkbox" name="hobby" value="배드민턴">배드민턴
	</td>
</tr>

<tr>
	<td colspan="2" style="text-align:center;">
		<input type="submit" value="전송">
		<input type="reset" value="초기화">
	</td>
</tr>

</table>


</form>
</body>
</html>















