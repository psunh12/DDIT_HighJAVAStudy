<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Request연습 Form(숫자 입력은 정수형으로...)</h3>
<hr>
<form action="/webTest/requestTest02.do" method="post">
<input type="text" size="10" name="num1">

<select name="op">
	<option value="+"> + </option>
	<option value="-"> - </option>
	<option value="*"> * </option>
	<option value="/"> / </option>
	<option value="%"> % </option>
</select>

<input type="text" size="10" name="num2">

<input type="submit" value="확인">

</form>
</body>
</html>