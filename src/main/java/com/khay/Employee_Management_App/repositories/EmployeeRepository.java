package com.khay.Employee_Management_App.repositories;

import com.khay.Employee_Management_App.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
