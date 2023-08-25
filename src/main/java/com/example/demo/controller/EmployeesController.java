package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomEmployeeRepo;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeesController {
	private EmployeeService employeeService;
    private CustomEmployeeRepo employeeRepository;


//    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
//        return employeeService.saveEmployee(employeeDto);
//    }

    
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Employee> saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/{id}")
    public Mono<EmployeeDto> getEmployee(@PathVariable("id") String employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping
    public Flux<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDto employeeDto,
                                            @PathVariable("id") String employeeId){
         employeeService.updateEmployee(employeeDto, employeeId);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEmployee(@PathVariable("id") String employeeId){
        return employeeService.deleteEmployee(employeeId);
    }
    

    
//    
//    
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Object> updateEmployee1(@RequestBody Employee employeeDto,
//                                            @PathVariable("id") String employeeId){
//         employeeService.update(employeeId,employeeDto);
//        return new ResponseEntity<>("Employee is updated successfully", HttpStatus.OK);
//
//    }
//    
    
    
    @PutMapping("/update/{id}")
	  public ResponseEntity<Employee> updateTutorial(@PathVariable("id") String id, @RequestBody Employee  tutorial) {
    	
    	
//		if (!employeeRepository.containsKey(id))
//		throw new ResourceNotFoundException("Not found Employee with id = " + id);
    	Employee _tutorial = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id = " + id));

	    _tutorial.setFirstName(tutorial.getFirstName());
	    _tutorial.setLastName(tutorial.getLastName());
	    _tutorial.setEmail(tutorial.getEmail());
	    
	    return new ResponseEntity<>(employeeRepository.save(_tutorial), HttpStatus.OK);
	  }

}
