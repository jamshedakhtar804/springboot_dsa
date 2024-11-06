package com.cache.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cache.demo.model.Department;
import com.cache.demo.repos.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	
	public List<Department> findAllDepartments(){
		
		return departmentRepository.findAll();
	}
	
//	@Cacheable(value="departmentCache", key="#deptId")
//	public Department findByDepartmentId(Integer deptId) {
//		return departmentRepository.findById(deptId).get();
//	}
	
	@Cacheable(value="applicationCache", key="#deptId")
	public Department findByDepartmentId(Integer deptId) {
		return departmentRepository.findById(deptId).get();
	}
	
	@CacheEvict(value="applicationCache", allEntries=true)
	public void clearAllCache() {
		System.out.println("**** All cache evicted ***");
	}
	
	@CacheEvict(value="applicationCache", key="#deptId")
	public void clearDataFromCache(Integer deptId) {
		System.out.println("**** Data evicted from Cache : "+deptId);
	}
}
