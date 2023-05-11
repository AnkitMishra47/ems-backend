package com.ems.services.impl;

import com.ems.dto.EmployeeDTO;
import com.ems.exception.ResourceNotFoundException;
import com.ems.model.Employee;
import com.ems.model.SearchEmployee;
import com.ems.repository.EmployeeRepository;
import com.ems.services.EmployeeService;
import com.ems.utils.EMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getAllEmployees(SearchEmployee searchEmployee) {
        List<Employee> employees = employeeRepository.searchWithEmptyKeyword(searchEmployee.getDetails());
        return EMSUtils.getEmployeesDTO(employees);
    }

    @Override
    public ResponseEntity<Map<String, Object>> createEmployee(EmployeeDTO employeeDTO) {
        Map<String, Object> response = new HashMap<>();

        if (employeeDTO.getName() == null || employeeDTO.getEmail() == null || employeeDTO.getMobile() == null){
            response.put("error" , EMSUtils.mandatoryValidation());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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

        response.put("ObjectID" , employee.getId());
        response.put("message" , "Details Saved Successfully");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<Long, String>> deleteEmployee(Long id) {
        this.employeeRepository.delete(getEmployeeByID(id));
        Map response = new HashMap();
        response.put(id , "Deleted Successfully");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(Long id) {
        return ResponseEntity.ok(getEmployeeDTOByID(id));
    }

    private EmployeeDTO getEmployeeDTOByID(Long id){
        Employee employee = getEmployeeByID(id);
        return new EmployeeDTO(employee);
    }

    private Employee getEmployeeByID(Long id){
        return this.employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee doesn't exist with this id"));
    }
}
