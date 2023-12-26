package com.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column
	@Size(min=4,max=30,message = "Employee Name should be between 4 - 30 Characters.")
	private String employeeName;
	
	@Column
	private String email;
	
	@Column
	private double salary;
	
	@Column
	private String role;
}
