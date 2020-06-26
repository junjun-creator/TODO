package kr.or.connect.todoproject.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.Todo.dao.TodoDao;
import kr.or.connect.Todo.dto.TodoDto;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/todotype")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String type = req.getParameter("type");
		System.out.println(id);
		System.out.println(type);
		TodoDto todoDto = new TodoDto(id,type);
		TodoDao todoDao = new TodoDao();
		int updateCount = todoDao.updateTodo(todoDto);
		if(type.equals("TODO")) {
			type = "DOING";
		}else if(type.equals("DOING")) {
			type = "DONE";
		}
		
		List list = new ArrayList();
		list.add(0,id);
		list.add(1, type);
	
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(list);
	}

}
