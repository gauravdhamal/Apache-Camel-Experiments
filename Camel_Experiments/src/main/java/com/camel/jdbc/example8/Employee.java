package com.camel.jdbc.example8;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipField = true, skipFirstLine = true)
public class Employee {

	@DataField(pos = 1)
	private Integer id;

	@DataField(pos = 2)
	private String name;

	@DataField(pos = 3)
	private String address;

	@DataField(pos = 4)
	private String role;

	public Employee(Integer id, String name, String address, String role) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.role = role;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", role=" + role + "]";
	}

}
