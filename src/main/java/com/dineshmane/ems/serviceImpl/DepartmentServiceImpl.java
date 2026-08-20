package com.dineshmane.ems.serviceImpl;

import com.dineshmane.ems.dto.DepartmentDTO;
import com.dineshmane.ems.entity.Department;
import com.dineshmane.ems.exception.ResourceNotFoundException;
import com.dineshmane.ems.repository.DepartmentRepository;
import com.dineshmane.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department with given id is not exists"));
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("Department with given doesn't exists"));
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(d->(modelMapper.map(d, DepartmentDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDepartmentByid(Long id) {
        if(!departmentRepository.existsById(id)) throw new ResourceNotFoundException("Department with given id is not present");
        departmentRepository.deleteById(id);
    }

}
