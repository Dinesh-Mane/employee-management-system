package com.dineshmane.ems.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String departmentName;
    @Column(nullable = false)
    private String departmentDescription;

    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;


}
