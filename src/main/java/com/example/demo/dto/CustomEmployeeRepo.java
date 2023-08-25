package com.example.demo.dto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.models.Employee;

public interface CustomEmployeeRepo extends   MongoRepository<Employee, String> {

//	boolean containsKey(String id);

}
