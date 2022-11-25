package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.hibernate.onetomany.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  List<Employee> findByDepartmentId(Long postId);
  
  @Transactional
  void deleteByDepartmentId(long tutorialId);
}
