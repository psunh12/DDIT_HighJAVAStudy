<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#tashuBtn").on("click", function(){
		$.ajax({
			url : "https://bikeapp.tashu.or.kr:50041/v1/openapi/station",
			type : "get",
			beforeSend: function (xhr) {
	            xhr.setRequestHeader("Content-type","application/json");
	            xhr.setRequestHeader("api-token", "fd8aod859twd00ha");
	        },
	        success : function(res){
	        	console.log("res", res);
	        },
	        error : function(xhr){
	        	console.log("error", xhr);
	        },
	        dataType : "json"
		});
	});
	
});

</script>
</head>
<body>
<input type="button" id="tashuBtn" value="타슈정보">
</body>
</html>