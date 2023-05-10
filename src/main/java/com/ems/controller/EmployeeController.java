package com.ems.controller;

import com.ems.exception.ResourceNotFoundException;
import com.ems.model.Employee;
import com.ems.model.EmployeeDTO;
import com.ems.model.SearchEmployee;
import com.ems.repository.EmployeeRepository;
import com.ems.utils.EMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/svc/api/")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/search-employees")
    public List<EmployeeDTO> getAllEmployees(@RequestBody SearchEmployee searchEmployee){
        List<Employee> employees = employeeRepository.searchWithEmptyKeyword(searchEmployee.getDetails());
        return EMSUtils.getEmployeesDTO(employees);
    }
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employeeDTO){

        Map<String, Employee> errorMessages = new HashMap<>();

        if (employeeDTO.getName() == null || employeeDTO.getEmail() == null || employeeDTO.getMobile() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mandatoryValidation());
        }

        Employee employee ;

        if (employeeDTO.getId() != null){
            employee = getEmployeeByID(employeeDTO.getId());
            employee.setGender(employeeDTO.getGender());
        }
        else {
            employee = new Employee(employeeDTO);
        }
        employeeRepository.save(employee);

        return ResponseEntity.ok("Details Saved Successfully");
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<Long , String>> deleteEmployee(@PathVariable Long id){
        this.employeeRepository.delete(getEmployeeByID(id));
        Map response = new HashMap();
        response.put(id , "Deleted Successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(getEmployeeDTOByID(id));
    }

    private EmployeeDTO getEmployeeDTOByID(Long id){
        Employee employee = getEmployeeByID(id);
        return new EmployeeDTO(employee);
    }

    private Employee getEmployeeByID(Long id){
        return this.employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee doesn't exist with this id")
                );
    }

    private String throwFieldNullException(String fieldName){
        return "Please enter the value for " + fieldName;
    }

    private String mandatoryValidation(){
        return "Please fill all the '*' mandatory fields.";
    }
}
