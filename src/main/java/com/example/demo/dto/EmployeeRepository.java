package com.example.demo.dto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;


//@Repository
public interface EmployeeRepository extends   ReactiveMongoRepository<Employee, String> {

//	boolean containsKey(String employeeId);

//	boolean containsKey(String employeeId);

}
