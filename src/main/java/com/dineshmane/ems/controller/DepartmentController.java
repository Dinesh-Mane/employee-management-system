package com.dineshmane.ems.controller;

import com.dineshmane.ems.dto.DepartmentDTO;
import com.dineshmane.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
@Builder
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartmentDTO = departmentService.addDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartmentDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> addDepartment(@PathVariable("id") Long department_id){
        DepartmentDTO savedDepartmentDTO = departmentService.getDepartmentById(department_id);
        return ResponseEntity.status(HttpStatus.OK).body(savedDepartmentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> addDepartment(@PathVariable("id") Long id, @RequestBody DepartmentDTO departmentDTO){
        departmentDTO.setId(id);
        DepartmentDTO savedDepartmentDTO = departmentService.updateDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedDepartmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> addDepartment(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllDepartments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id){
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Department Deleted!");
    }
}
