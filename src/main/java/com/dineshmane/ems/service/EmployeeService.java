package com.dineshmane.ems.service;

import com.dineshmane.ems.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(Long departmentId, EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById(Long departmentId, Long employeeId);
    List<EmployeeDTO> getAllEmployeesByDepartmentId(Long departmentId);
    EmployeeDTO updateEmployee(Long departmentId, Long employeeId, EmployeeDTO employeeDTO);
    void deleteEmployeeById(Long departmentId, Long employeeId);
}
