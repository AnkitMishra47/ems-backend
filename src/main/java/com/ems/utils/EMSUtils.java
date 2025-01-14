package com.ems.utils;
import com.ems.model.Employee;
import com.ems.dto.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;

public class EMSUtils {

    public static String EMPLOYEE_ROLE  = "EMPLOYEE_ROLE";
    public static String ADMIN_ROLE     = "ADMIN_ROLE";

    public static List<EmployeeDTO> getEmployeesDTO(List<Employee> employees)
    {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees)
        {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            employeeDTOs.add(employeeDTO);
        }

        return employeeDTOs;
    }

    private String throwFieldNullException(String fieldName){
        return "Please enter the value for " + fieldName;
    }

    public static String mandatoryValidation()
    {
        return "Please fill all the '*' mandatory fields.";
    }

    public static String invalid()
    {
        return "Invalid Username and Password";
    }

}
