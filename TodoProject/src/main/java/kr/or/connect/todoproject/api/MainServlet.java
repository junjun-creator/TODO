package kr.or.connect.todoproject.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.Todo.dao.TodoDao;
import kr.or.connect.Todo.dto.TodoDto;


/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		TodoDao todoDao = new TodoDao();
		List<TodoDto> list = new ArrayList<>();
		
		list = todoDao.getTodos();
		
		req.setAttribute("list", list); // request scope에 list 저장해서 main.jsp로 포워딩 할것임.
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main.jsp"); //forwarding 보내는 코드
		requestDispatcher.forward(req, resp);
	}

}
