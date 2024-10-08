package com.boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.Repository.EmployeeRepository;
import com.boot.dto.Employee;



@Controller
public class EmployeeController 
{
	@Autowired
	EmployeeRepository repo;
	@GetMapping("/")
	public String home() 
	{
		return "home.html";
	}
	
	@GetMapping("/add")
	public String addEmployee()
	{
		return "add.html";
	}
	

	@PostMapping("/add")
	public String addEmployee(Employee employee, ModelMap map)
	{
		repo.save(employee);
		map.put("employe", "Employee Added");
		return "home.html";
	}
	
	@GetMapping("/viewall")
	public String findall(ModelMap map)
	{
		map.addAttribute("employees", repo.findAll());
		return "find.html";
	}
	
	@GetMapping("/delete")
	public String removeEmployee(@RequestParam("id") Integer id, ModelMap map)
	{
		//map.put("employe", "Employee Escaped.....");
		if(id!=null)
		{
			repo.deleteById(id);
		}
		return "redirect:/viewall";
	}
	
	@GetMapping("/edit")
	public String editEmployee(@RequestParam("id") Integer id, ModelMap map) {
		Employee employee = repo.findById(id).orElseThrow();
		map.put("employee", employee);
	    return "edit.html";
	}

	
	@PostMapping("/addemp")
	public String updateEmployee(@RequestParam("id") Integer id , Employee employee)
	{
		repo.save(employee);
		
		return "redirect:/viewall";
	}
	@GetMapping("/viewbyid")
	public String getEmployeeById() {
		return "employeeById.html";
	}
	
	@GetMapping("/display")
	public String displayEmployeeById( @RequestParam("id") Integer id,ModelMap map) 
	{
		Optional<Employee> employe = repo.findById(id);
		if (employe.isPresent()) {
	        map.addAttribute("employee", employe.get());
	    } else {
	        map.addAttribute("errorMessage", "Employee with ID " + id + " not found.");
	    }
		return "employeeById.html";
	}
	@GetMapping("/viewbyname")
	public String getEmployeeByName() {
		return "employeeByName.html";
	}
	
	@GetMapping("/displayByName")
	public String displayEmployeeByName( @RequestParam("name") String name,ModelMap map) 
	{
		List<Employee> employee = repo.findByName(name);
		if(employee.isEmpty())
		{
	        map.addAttribute("errorMessage", "Employee with Name " + name + " not found.");
		}
		else	map.addAttribute("employees",employee);
		
		return "employeeByName.html";
	}
	
	@GetMapping("/viewbysal")
	public String getEmployeeBySalary() {
		return "employeeBysalary.html";
	}
	
	@GetMapping("/displayBysal")
	public String displayEmployeeBySalary( @RequestParam("salary") double salary,ModelMap map) 
	{
		List<Employee> sal = repo.findBySalary(salary);
		if(sal.isEmpty())
		{
	        map.addAttribute("errorMessage", "Employee with Salary " + salary + " not found.");
		}
		else
		map.addAttribute("employees",sal);
		
		return "employeeBysalary.html";
	}
}
