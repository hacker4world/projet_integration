package com.topone.projet_integration.repositories;

import com.topone.projet_integration.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByEmail(String email);


    @Query("SELECT e FROM Employee e WHERE e.accountAccepted = 0")
    List<Employee> findPendingEmployeeRequests();

}
