package demo;
import java.io.Serializable;
import java.util.Map;

public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2448260736229612919L;//序列化id
	private int id;//学生记录的id
	private String stuName;//姓名
	private int age;//年龄
	private int gender;//性别
	private String address;//地址
	public Student(){
		super();
	}
	public Student(int id, String stuName, int age, int gender, String address) {
		super();
		this.id = id;
		this.stuName = stuName;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
	/*
	 * 构造函数，将查询到的Map类型的数据构造成学生对象
	 */
	public Student(Map<String,Object> map){
		this.id = (int)map.get("id");
		this.stuName = (String)map.get("stu_name");
		this.age = (int)map.get("age");
		this.gender = (int)map.get("gender");
		this.address = (String)map.get("address");
	}
	public String getStuName() {
		
		return stuName;
	}
	public int getGender() {
		return gender;
	}
	public void setStuName(String stuName2) {
		this.stuName = stuName2;
		
	}
	
	public void setGender (int gender2) {
		this.gender = gender2;
		
	}
}
