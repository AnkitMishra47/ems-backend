package com.ems.utils;

import com.ems.model.Employee;
import com.ems.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EMSUtils {

    public static List<EmployeeDTO> getEmployeesDTO(List<Employee> employees){
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            employeeDTOs.add(employeeDTO);
        }

        return employeeDTOs;
    }
}
