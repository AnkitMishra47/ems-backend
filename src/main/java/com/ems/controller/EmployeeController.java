package com.ems.controller;

import com.ems.exception.ResourceNotFoundException;
import com.ems.model.Employee;
import com.ems.model.SearchEmployee;
import com.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/svc/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/search-employees")
    public List<Employee> getAllEmployees(@RequestBody SearchEmployee searchEmployee){
        return employeeRepository.searchWithEmptyKeyword(searchEmployee.getDetails());
    }
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        if (employee.getName() == null){
            throwFieldNullException("Name");
        }
        if (employee.getEmail() == null){
            throwFieldNullException("Email");
        }
        if (employee.getMobile() == null){
            throwFieldNullException("Mobile");
        }
        Employee newEmployee =  employeeRepository.save(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<Long , String>> deleteEmployee(@PathVariable Long id){
        this.employeeRepository.delete(getEmployeeByID(id));
        Map response = new HashMap();
        response.put(id , "Deleted Successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(getEmployeeByID(id));
    }

    private Employee getEmployeeByID(Long id){
        return this.employeeRepository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Employee doesn't exist with this id")
                );
    }

    private void throwFieldNullException(String fieldName){
        throw new NullPointerException("Please enter the value for " + fieldName);
    }
}
