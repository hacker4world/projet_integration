package com.topone.projet_integration.Repository;

import com.topone.projet_integration.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findByEmail(String email);
}
