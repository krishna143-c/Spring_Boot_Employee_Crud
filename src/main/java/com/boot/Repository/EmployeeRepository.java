package com.boot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{
	public  List<Employee> findByName(String name);
	public  List<Employee> findBySalary(double salary);;
}
