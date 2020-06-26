package kr.or.connect.Todo.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import kr.or.connect.Todo.dto.TodoDto;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?useUnicode=yes&characterEncoding=UTF-8"; //sql에서 한글깨짐이 없도록하는 코드 추가
	private static String user = "connectuser";
	private static String password = "connect123!@#";
	
	//일단 addTodo, getTodos 작성 완료. updateTodo 작성해야함.
	
	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl,user,password);
			String sql = "UPDATE todo SET type = ? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			System.out.println(todoDto.getType());
			if(todoDto.getType().equals("TODO")) {
				ps.setString(1, "DOING");
				ps.setInt(2, todoDto.getId());
			}else if(todoDto.getType().equals("DOING")) {
				ps.setString(1, "DONE");
				ps.setInt(2, todoDto.getId());
			}
			
			updateCount = ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return updateCount;
	}
	
	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl,user,password);
			String sql = "INSERT INTO todo(title,name,sequence) VALUES(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());
			
			insertCount = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return insertCount;
	}
	
	public List<TodoDto> getTodos(){
		List<TodoDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl,user,password);
			String sql = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY regdate DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int sequence = rs.getInt(4);
				String type = rs.getString(5);
				Date regdate = rs.getDate(6);
				
				TodoDto todoDto = new TodoDto(id,title,name,sequence,type,regdate);
				list.add(todoDto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
