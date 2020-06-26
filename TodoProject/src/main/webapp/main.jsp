<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.connect.Todo.dao.TodoDao" %>
<%@ page import="kr.or.connect.Todo.dto.TodoDto" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
<style>
        header{
            display : flex;
            flex-direction: row-reverse;
        }
        .new_todo{
            display: flex;
            justify-content: center;
            align-items: center;
            height:50px;
            width:150px;
            background-color: powderblue;
        }
        section{
            display: flex;
            flex-direction: row;
            justify-content: center;
        }
        .main_todo{
            display:flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 300px;
            height:50px;
            background-color: rebeccapurple;
            color:white;
            margin:30px;
        }
        .main_doing{
            display:flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 300px;
            height:50px;
            background-color: rebeccapurple;
            color:white;
            margin:30px;
        }
        .main_done{
            display:flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 300px;
            height:50px;
            background-color: rebeccapurple;
            color:white;
            margin:30px;
        }
        .todo_list{
            display: flex;
            flex-direction: column;
            width:300px;
            height:50px;
            background-color: thistle;
            margin:30px;
            justify-content: center;
            position: relative;
        }
        .todo_id{
        	display : none;
        }
        .todo_list{
            display: flex;
            flex-direction: column;
            width:300px;
            height:50px;
            background-color: thistle;
            margin:30px;
            justify-content: center;
            position: relative;
        }
        .todo_content{
            font-size: smaller;
        }
        .todo_dates{
            font-size:x-small;
        }
        .todo_button{
            position: absolute;
            bottom: 0;
            right:0;
            margin:5px;
            width:10px;
            height:10px;
        }
    </style>
</head>
<body>
	<header>
        <a class="new_todo" href="./todoform">새로운 할일 등록</a>
    </header>
    <section>
        <main id='todo'>
            <div class="main_todo">TODO</div>
            <%
            	
            	List<TodoDto> list = new ArrayList<>();
            	list = (List<TodoDto>)request.getAttribute("list");
            	
            	for(TodoDto todoDto : list){
            		if(todoDto.getType().equals("TODO")){
            %>
           	<div class = "todo_list">
           		<span class="todo_id"><%=todoDto.getId()%></span>
           		<span class = "todo_content"><strong><%=todoDto.getTitle()%></strong></span>
           		<span class = "todo_dates">등록날짜:<%=todoDto.getRegdate()%> <%=todoDto.getName()%> 우선순위:<%=todoDto.getSequence()%></span>
           		<input type="button" class="todo_button" />
           	</div>
            <%
            		}
            	}
            %>
        </main>
        <main id='doing'>
            <div class="main_doing">DOING</div>
            <%
            	for(TodoDto todoDto : list){
            		if(todoDto.getType().equals("DOING")){
            %>
           	<div class = "todo_list">
           		<span class="todo_id"><%=todoDto.getId()%></span>
           		<span class = "todo_content"><strong><%=todoDto.getTitle()%></strong></span>
           		<span class = "todo_dates">등록날짜:<%=todoDto.getRegdate()%> <%=todoDto.getName()%> 우선순위:<%=todoDto.getSequence()%></span>
           		<input type="button" class="todo_button" />
           	</div>
            <%
            		}
            	}
            %>
        </main>
        <main id='done'>
            <div class="main_done">DONE</div>
            <%
            	for(TodoDto todoDto : list){
            		if(todoDto.getType().equals("DONE")){
            %>
           	<div class = "todo_list">
           		<span class = "todo_content"><strong><%=todoDto.getTitle()%></strong></span>
           		<span class = "todo_dates">등록날짜:<%=todoDto.getRegdate()%> <%=todoDto.getName()%> 우선순위:<%=todoDto.getSequence()%></span>
           		
           	</div>
            <%
            		}
            	}
            %>
        </main>
    </section>
    <script>
    	var qS1 = document.querySelectorAll(".todo_button");
    	for(var i=0;i<qS1.length;i++){
    		qS1[i].addEventListener('click', function(event){
        		var xhr = new XMLHttpRequest();
        		xhr.open('post','./todotype');
        		xhr.onreadystatechange=function(){
        			if(xhr.readyState === 4 && xhr.status === 200){
        				alert('success');
        				var update = xhr.response;
        				//console.log(update.indexOf("DOING")); // response 문자열에 DOING이 있는지 확인. 없으면 -1, 있으면 양수(해당문자위치index)
        				//console.log(update.substring(4,9));  // response문자열에서 DOING만 빼오기.
        				//var inner =	u.firstChild.nextSibling.innerText; // 진짜 태그안의 text만 가져옴. title,name,regdate 정보들.
        				//console.log(inner);
        				if(update.indexOf("DOING")!= -1){ // response에 DOING이 있을때
        					var p = document.getElementById('doing'); //id='doing'인 태그의 자식으로 클릭한 해당 u태그를 넘김.
        					p.appendChild(u);
        				}else if(update.indexOf("DONE")!= -1){ // response에 DONE이 있을때
        					var p = document.getElementById('done'); //id='done'인 태그의 자식으로 클릭한 해당 u태그를 넘김.
        					p.appendChild(u);
        					var remove = p.lastChild.lastChild.previousSibling;
        					console.log(remove);
        					p.lastChild.removeChild(remove); // 완료된 todo는 이동버튼이 필요하지 않으므로 삭제.
        				}
        			}
        		}
        		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); //전송할때 형식을 이름=값 & 이름=값 형식으로 하겟다.
        		var data ='';
        		var u = this.parentNode;
        		var id = u.firstChild.nextSibling.firstChild.nodeValue;
        		var type = u.parentNode.firstChild.nextSibling.innerText;
        		console.log(type);
        		data += 'id='+id;
        		data += '&type='+type;
        		
    			xhr.send(data);
        	});
    	}
    	
    </script>
</body>
</html>