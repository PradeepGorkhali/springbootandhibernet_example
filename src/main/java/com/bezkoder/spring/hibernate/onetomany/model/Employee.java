package com.bezkoder.spring.hibernate.onetomany.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
  private Long id;

  @Lob
  private String emp_name;

//  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "department_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Department department;

  public Long getId() {
    return id;
  }

  public String getemp_name() {
    return emp_name;
  }

  public void setemp_name(String emp_name) {
    this.emp_name = emp_name;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

}
