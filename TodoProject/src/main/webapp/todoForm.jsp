<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %> <!-- 한글깨짐 방지. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>할일 등록</header>
    <form action="./todoadd" method="post" accept-charset="UTF-8"> <!-- accept-charset을 utf8로 설정하면 한글 깨짐 방지됨. -->
        <p>어떤일인가요?</p>
        <input type="text" name="todo_title" id="todo_title">
        <p>누가 할일인가요?</p>
        <input type="text" name="todo_name" id="todo_name">
        <p>우선순위를 선택하세요</p>
        <p>
            <input type="radio" name="sequence" value="1"> 1순위
            <input type="radio" name="sequence" value="2"> 2순위
            <input type="radio" name="sequence" value="3"> 3순위
        </p>
        <a href="./main">&lt; 이전</a>
        <input type="submit" value="제출">
        <input type="reset">
    </form>
</body>
</html>