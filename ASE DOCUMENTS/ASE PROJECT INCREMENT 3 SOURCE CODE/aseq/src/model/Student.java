package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.datatables.wrapper.annotation.AoColumn;

public class Student {
	@AoColumn(sTitle = "ID")
	private int id;
	
	@AoColumn(sTitle = "Name")
	@JsonInclude(Include.ALWAYS)
	private String name;
	
	@AoColumn(sTitle = "Age")
	private int age;
	
	@AoColumn(sTitle = "Class")
	@JsonInclude(Include.ALWAYS)
	private String clazz;
	
	@AoColumn(sTitle = "Address", bSearchable = false)
	@JsonInclude(Include.ALWAYS)
	private String address;
	
	public Student(){}
	
	
	
	public Student(int id, String name, int age, String clazz, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.clazz = clazz;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
