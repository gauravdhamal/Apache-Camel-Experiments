package com.csv.readwrite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csv.readwrite.dto.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
