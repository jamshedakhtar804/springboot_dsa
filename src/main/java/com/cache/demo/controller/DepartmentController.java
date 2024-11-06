package com.cache.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cache.demo.model.Department;
import com.cache.demo.service.DepartmentService;



@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/dept/findAll")
	public List<Department> findAll() {
		return departmentService.findAllDepartments();
	}

	
	@GetMapping("/dept/find/{id}")
	public Department findById(@PathVariable Integer id) {
		return departmentService.findByDepartmentId(id);
	}

//	@GetMapping("/dept/clearAllCache")
//	public String clearAllCache() {
//		departmentService.clearAllCache();
//		return "Data cleared from cache";
//	}
//	
//	@GetMapping("/dept/clearDataFromCache/{id}")
//	public String clearDataFromCache(@PathVariable Integer id) {
//		departmentService.clearDataFromCache(id);
//		return id+" cleared from cache";
//	}
	
}
