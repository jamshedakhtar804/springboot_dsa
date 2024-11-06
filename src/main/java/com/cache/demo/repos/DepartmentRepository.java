package com.cache.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cache.demo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
