package kr.or.connect.Todo.dto;

import java.sql.Date;

public class TodoDto {
	private Integer id;
	private String title;
	private String name;
	private Integer sequence;
	private String type;
	private Date regdate;
	
	public TodoDto() {
		
	}
	public TodoDto(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public TodoDto(String title, String name, Integer sequence) {
		super();
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}
	public TodoDto(Integer id,String title, String name, Integer sequence, String type, Date regdate) {
		super();
		this.id = id;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regdate = regdate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type=" + type
				+ ", regdate=" + regdate + "]";
	}
	
}
