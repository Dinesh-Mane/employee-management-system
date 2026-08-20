package com.dineshmane.ems.serviceImpl;

import com.dineshmane.ems.dto.EmployeeDTO;
import com.dineshmane.ems.entity.Department;
import com.dineshmane.ems.entity.Employee;
import com.dineshmane.ems.exception.BadRequestException;
import com.dineshmane.ems.exception.ResourceNotFoundException;
import com.dineshmane.ems.repository.DepartmentRepository;
import com.dineshmane.ems.repository.EmployeeRepository;
import com.dineshmane.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDTO addEmployee(Long departmentId, EmployeeDTO employeeDTO) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department not found with id: "+departmentId));

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setDept(department);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDTO savedEmployeeDTO = modelMapper.map(savedEmployee, EmployeeDTO.class);
        savedEmployeeDTO.setDepartmentId(savedEmployee.getDept().getId());
        return savedEmployeeDTO;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department not found with Department id: "+departmentId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found with id: "+employeeId));

        if(!employee.getDept().getId().equals(department.getId())){
            throw new BadRequestException("This Employee does not belong to Department with department id: "+departmentId);
        }
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        employeeDTO.setDepartmentId(employee.getDept().getId());
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + departmentId));

        List<Employee> employees = employeeRepository.findByDeptId(departmentId);

        return employees.stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
                    employeeDTO.setDepartmentId(employee.getDept().getId());
                    return employeeDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long departmentId, Long employeeId, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department not found with department id: "+departmentId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found with employee id: "+employeeId));

        if(!employee.getDept().getId().equals(departmentId)){
            throw new BadRequestException("Employee does not belong to department with department id: "+departmentId);
        }
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());

        Employee saved = employeeRepository.save(employee);
        EmployeeDTO savedDTO = modelMapper.map(saved,EmployeeDTO.class);
        savedDTO.setDepartmentId(saved.getDept().getId());

        return savedDTO;
    }

    @Override
    public void deleteEmployeeById(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not found with department id: "+ departmentId));
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found with employee id: "+employeeId));

        if(!employee.getDept().getId().equals(departmentId)){
            throw new BadRequestException("this employee does not belong to department with department id: "+departmentId);
        }
        employeeRepository.delete(employee);
    }


}
