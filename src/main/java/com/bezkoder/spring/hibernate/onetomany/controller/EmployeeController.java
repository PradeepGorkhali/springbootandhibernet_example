package com.bezkoder.spring.hibernate.onetomany.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Employee;
import com.bezkoder.spring.hibernate.onetomany.repository.EmployeeRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.DepartmentRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @GetMapping("/departments/{departmentId}/employee")
  public ResponseEntity<List<Employee>> getAllEmployeeByDepartmentsId(@PathVariable(value = "departmentId") Long departmentId) {
    if (!departmentRepository.existsById(departmentId)) {
      throw new ResourceNotFoundException("Not found Department with id = " + departmentId);
    }

    List<Employee> comments = employeeRepository.findByDepartmentId(departmentId);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  @GetMapping("/employee/{id}")
  public ResponseEntity<Employee> getEmployeeByDepartmentsId(@PathVariable(value = "id") Long id) {
    Employee comment = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id = " + id));

    return new ResponseEntity<>(comment, HttpStatus.OK);
  }

  @PostMapping("/departments/{departmentId}/employee")
  public ResponseEntity<Employee> createComment(@PathVariable(value = "departmentId") Long departmentId,
                                                @RequestBody Employee commentRequest) {
    Employee comment = departmentRepository.findById(departmentId).map(tutorial -> {
      commentRequest.setDepartment(tutorial);
      return employeeRepository.save(commentRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Department with id = " + departmentId));

    return new ResponseEntity<>(comment, HttpStatus.CREATED);
  }

  @PutMapping("/employee/{id}")
  public ResponseEntity<Employee> updateComment(@PathVariable("id") long id, @RequestBody Employee commentRequest) {
    Employee comment = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

    comment.setemp_name(commentRequest.getemp_name());

    return new ResponseEntity<>(employeeRepository.save(comment), HttpStatus.OK);
  }

  @DeleteMapping("/employee/{id}")
  public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
    employeeRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/tutorials/{departmentId}/employee")
  public ResponseEntity<List<Employee>> deleteAllCommentsOfTutorial(@PathVariable(value = "departmentId") Long departmentId) {
    if (!departmentRepository.existsById(departmentId)) {
      throw new ResourceNotFoundException("Not found Tutorial with id = " + departmentId);
    }

    employeeRepository.deleteByDepartmentId(departmentId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
