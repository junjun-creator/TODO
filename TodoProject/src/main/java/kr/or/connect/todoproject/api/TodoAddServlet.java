package kr.or.connect.todoproject.api;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.Todo.dao.TodoDao;
import kr.or.connect.Todo.dto.TodoDto;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/todoadd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글깨짐방지
		String title = request.getParameter("todo_title");
		String name = request.getParameter("todo_name");
		String seq = request.getParameter("sequence");
		int sequence = Integer.parseInt(seq);
		
		TodoDao todoDao = new TodoDao();
		TodoDto todoDto = new TodoDto(title,name,sequence);
		todoDao.addTodo(todoDto);
		
		response.sendRedirect("./main");
	}

}
