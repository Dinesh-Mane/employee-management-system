package com.dineshmane.ems.service;

import com.dineshmane.ems.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO addDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO getDepartmentById(Long id);
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartments();
    void deleteDepartmentById(Long id);
}
