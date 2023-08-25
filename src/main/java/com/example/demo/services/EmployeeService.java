package com.example.demo.services;

import java.util.Optional;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.models.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);

    Mono<EmployeeDto> getEmployee(String employeeId);

    Flux<EmployeeDto> getAllEmployees();

    Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId);

    Mono<Void> deleteEmployee(String employeeId);

	Mono<Employee> saveEmployee(Employee employeeDto);

	Mono<Employee> update(String employeeId, Employee employeeDto);
}
