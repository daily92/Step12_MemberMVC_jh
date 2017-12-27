<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/member/insertform.jsp</title>
</head>
<body>
	<h3>회원 추가 폼 입니다.</h3>
			<!--  /member/insert  -->
	<%-- 현재 주소창 위치 잘 파악 할 것!!
			http://localhost:8888/Step12_MemberMVC_jh/member/insertform.do 
		현재 위치는 /member 안 임!
		
		추가 버튼 누르면 
			http://localhost:8888/Step12_MemberMVC_jh/member/insert.do
		command => /member/insert
	--%>
	<form action="insert.do" method="post">
		이름 <input type="text" name="name" id="name"/>
		주소 <input type="text" name="addr" id="addr"/>
		<button type="submit">추가</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>