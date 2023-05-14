package com.ems.controller;

import com.ems.dto.EmployeeDTO;
import com.ems.model.SearchEmployee;
import com.ems.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/svc/api/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/search-employees")
    public List<EmployeeDTO> getAllEmployees(@RequestBody SearchEmployee searchEmployee){
       return employeeService.getAllEmployees(searchEmployee);
    }
    @PostMapping("/employees")
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<Long , String>> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }
}
