package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.hibernate.onetomany.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
  List<Department> findByPublished(boolean published);

  List<Department> findByTitleContaining(String title);
}
