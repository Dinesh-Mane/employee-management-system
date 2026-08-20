package com.dineshmane.ems.serviceImpl;

import com.dineshmane.ems.dto.DepartmentDTO;
import com.dineshmane.ems.entity.Department;
import com.dineshmane.ems.exception.DepartmentNotExistsException;
import com.dineshmane.ems.repository.DepartmentRepository;
import com.dineshmane.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;


    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(department);

        return new DepartmentDTO(savedDepartment.getId(), savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDescription());
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id).orElseThrow(()-> new DepartmentNotExistsException("Department with given id is not exists"));
        return new DepartmentDTO(department.getId(), department.getDepartmentName(), department.getDepartmentDescription());
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentRepository.getDepartmentById(departmentDTO.getId()).orElseThrow(()-> new DepartmentNotExistsException("Department with given doesn't exists"));
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        Department savedDepartment = departmentRepository.save(department);
        return new DepartmentDTO(savedDepartment.getId(), savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDescription());
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(d->(new DepartmentDTO(d.getId(), d.getDepartmentName(), d.getDepartmentDescription())))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDepartmentByid(Long id) {
        departmentRepository.deleteById(id);
    }


}
