package com.example.demo.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeRepository;
import com.example.demo.mapper.EmployeeMapper;
//import com.example.demo.exception.EmployeeNotfoundException;
//import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.models.Employee;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Override
	public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
		if (Objects.nonNull(employeeDto)) {

			Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
			Mono<Employee> savedEmployee = employeeRepository.save(employee);
			return savedEmployee.map((employeeEntity) -> EmployeeMapper.mapToEmployeeDto(employeeEntity));
		} else {
			return null;
		}
	}

	@Override
	public Mono<Employee> saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
//        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
//        Mono<Employee> savedEmployee = employeeRepository.save(employee);
//        return savedEmployee
//                .map((employeeEntity) -> EmployeeMapper.mapToEmployeeDto(employeeEntity));

	}

	@Override
	public Mono<EmployeeDto> getEmployee(String employeeId) {
		Mono<Employee> employeeMono = employeeRepository.findById(employeeId);
		return employeeMono.map((employee -> EmployeeMapper.mapToEmployeeDto(employee)));
	}

	@Override
	public Flux<EmployeeDto> getAllEmployees() {

		Flux<Employee> employeeFlux = employeeRepository.findAll();
		return employeeFlux.map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId) {

//		if (!(employeeRepository).containsKey(employeeId))
//			throw new EmployeeNotfoundException();

		Mono<Employee> employeeMono = employeeRepository.findById(employeeId);
		
//		if(employeeMono.con) {
//			
//		
//				
//		}
		return 
				
				
				employeeMono.flatMap((existingEmployee) -> {
			existingEmployee.setFirstName(employeeDto.getFirstName());
			existingEmployee.setLastName(employeeDto.getLastName());
			existingEmployee.setEmail(employeeDto.getEmail());
			return employeeRepository.save(existingEmployee);
		}).map((employee -> EmployeeMapper.mapToEmployeeDto(employee)));
		

		
	}

	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String id, @RequestBody EmployeeDto employeeDetails) {

//		EmployeeDto updateEmployee = employeeRepository.containsKey(id);
//		if(!employeeRepository.containsKey(id))throw new EmployeeNotfoundException();
//				.orElseThrow(() -> new ProductNotfoundException("Product not exist with id: " + id));
		return null;

//		updateEmployee.setProductName(employeeDetails.getProductName());
//
//		productdao.save(updateEmployee);
//
//		return ResponseEntity.ok(updateEmployee);
	}

	private Object mapToEmployeeDto(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteEmployee(String employeeId) {
		return employeeRepository.deleteById(employeeId);
	}

	@Override
	public Mono<Employee> update(String id, Employee tutorial) {
		return employeeRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(optionalTutorial -> {
					if (optionalTutorial.isPresent()) {
						tutorial.setId(id);
						return employeeRepository.save(tutorial);
					}

					return Mono.empty();
				});
	}
	
	
	
	
	
	
	
	
	
}