package com.dineshmane.ems.repository;

import com.dineshmane.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    List<Employee> findByDeptId(Long department_id);
    List<Employee> findByDeptId(Long departmentId);
}