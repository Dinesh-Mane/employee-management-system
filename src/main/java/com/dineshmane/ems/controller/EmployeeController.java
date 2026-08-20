package com.dineshmane.ems.controller;

import com.dineshmane.ems.dto.EmployeeDTO;
import com.dineshmane.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping("/{department_id}")
    public ResponseEntity<EmployeeDTO> addEmployee(@PathVariable("department_id") Long departmentId, @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(departmentId, employeeDTO));
    }

    @GetMapping("/{department_id}/employees/{employee_id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("department_id") Long deptId, @PathVariable("employee_id") Long empId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(deptId,empId));
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesByDepartmentId(
            @PathVariable("departmentId") Long departmentId) {

        List<EmployeeDTO> employees = employeeService.getAllEmployeesByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/{dept_id}/employees/{emp_id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("dept_id") Long departmentId, @PathVariable("emp_id") Long employeeId, @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(departmentId,employeeId,employeeDTO));
    }


    @DeleteMapping("/{deptId}/employees/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("deptId") Long departmentId, @PathVariable("empId") Long employeeId){
        employeeService.deleteEmployeeById(departmentId,employeeId);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted!");
    }
}
