package com.ems.repository;

import com.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:keyword% OR e.email LIKE %:keyword% OR e.mobile LIKE %:keyword%")
    List<Employee> search(@Param("keyword") String keyword);

    default List<Employee> searchWithEmptyKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        } else {
            return search(keyword);
        }
    }
}
