package com.csv.readwrite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csv.readwrite.dto.Employee;
import com.csv.readwrite.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl {

	@Autowired
	public EmployeeRepo employeeRepo;

	public Employee saveEmp(Employee employee) {
		return employeeRepo.save(employee);
	}

}
