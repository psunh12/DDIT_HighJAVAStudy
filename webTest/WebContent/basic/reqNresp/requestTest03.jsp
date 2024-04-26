<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 
jsp문서에서 년도를 입력받아 서버로 전송하면
서버에서는 해당 년도가 윤년인지 평년인지를 판정해서 
그 결과를 응답으로 보내는 프로그램을 완성하시오.

- jsp문서명 : requestTest03.jsp
- 서블릿 클래스명 : RequestTest03.java
- 서블릿 url패턴 : /requestTest03.do 

-->
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/webTest/requestTest03.do">
년도 입력 : <input type="text" name="year">
<input type="submit" value=" 전송 ">

</form>
</body>
</html>





