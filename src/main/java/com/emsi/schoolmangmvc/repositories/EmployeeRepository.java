package com.emsi.schoolmangmvc.repositories;

import com.emsi.schoolmangmvc.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findByFullnameContains (String kw, Pageable pageable);
}
