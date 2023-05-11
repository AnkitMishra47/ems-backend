package com.ems.services;

import com.ems.dto.EmployeeDTO;
import com.ems.dto.UserRegistrationDTO;
import com.ems.model.Employee;
import com.ems.model.SearchEmployee;
import com.ems.model.User;
import com.ems.utils.EMSUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public List<EmployeeDTO> getAllEmployees( SearchEmployee searchEmployee);

    public ResponseEntity<Map<String, Object>> createEmployee( EmployeeDTO employeeDTO);

    public ResponseEntity<Map<Long , String>> deleteEmployee( Long id);

    public ResponseEntity<EmployeeDTO> getEmployeeById(Long id);




}
