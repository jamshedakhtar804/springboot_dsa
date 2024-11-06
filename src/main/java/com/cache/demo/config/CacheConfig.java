package com.cache.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cache.demo.model.Department;
import com.cache.demo.service.DepartmentService;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private DepartmentService departmentService;
	
//	@PostConstruct
//	public void preloadCached() {
//		
//		Cache cache = cacheManager.getCache("applicationCache");
//		System.out.println("****** Initializing Cache");
//		List<Department> allDepartments = departmentService.findAllDepartments();
//		
//		for(Department department : allDepartments) {
//			cache.put(department.getId(), department);
//		}
//	}
	
	@PostConstruct
	public void preloadCached() {
	    try {
	        Cache cache = cacheManager.getCache("applicationCache");
	        System.out.println("****** Initializing Cache");
	        List<Department> allDepartments = departmentService.findAllDepartments();
	        
	        for (Department department : allDepartments) {
	            cache.put(department.getId(), department);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // This will give more detailed error output.
	    }
	}

	
	@Scheduled(fixedRate = 15000, initialDelay = 15000)
	public void clearCache() {
		System.out.println("****** clearing the Cache");
		cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
	}

}
