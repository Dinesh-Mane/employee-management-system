package com.dineshmane.ems.repository;

import com.dineshmane.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> getDepartmentById(Long id);

    @Override
    boolean existsById(Long aLong);
}
