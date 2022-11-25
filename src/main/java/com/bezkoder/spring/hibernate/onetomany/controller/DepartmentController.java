package com.bezkoder.spring.hibernate.onetomany.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Department;
import com.bezkoder.spring.hibernate.onetomany.repository.DepartmentRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DepartmentController {

  @Autowired
  DepartmentRepository departmentRepository;

  @GetMapping("/departments")
  public ResponseEntity<List<Department>> getAllTutorials(@RequestParam(required = false) String name) {
    List<Department> departments = new ArrayList<Department>();

    if (name == null)
      departmentRepository.findAll().forEach(departments::add);
    else
      departmentRepository.findByPublished(Boolean.parseBoolean(name)).forEach(departments::add);

    if (departments.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(departments, HttpStatus.OK);
  }

  @GetMapping("/departments/{id}")
  public ResponseEntity<Department> getDepartmentId(@PathVariable("id") long id) {
    Department department = departmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Department with id = " + id));

    return new ResponseEntity<>(department, HttpStatus.OK);
  }

  @PostMapping("/departments")
  public ResponseEntity<Department> createDepartment(@RequestBody Department departments) {
    Department _departments = departmentRepository.save(new Department(departments.getTitle(), departments.getDescription(), true));
    return new ResponseEntity<>(_departments, HttpStatus.CREATED);
  }

  @PutMapping("/departments/{id}")
  public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department departments) {
    Department _departments = departmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Department with id = " + id));

    _departments.setTitle(departments.getTitle());
    _departments.setDescription(departments.getDescription());
    _departments.setPublished(departments.isPublished());
    
    return new ResponseEntity<>(departmentRepository.save(_departments), HttpStatus.OK);
  }

  @DeleteMapping("/departments/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    departmentRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/departments")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    departmentRepository.deleteAll();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/departments/published")
  public ResponseEntity<List<Department>> findByPublished() {
    List<Department> tutorials = departmentRepository.findByTitleContaining(String.valueOf(true));

    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }
}
