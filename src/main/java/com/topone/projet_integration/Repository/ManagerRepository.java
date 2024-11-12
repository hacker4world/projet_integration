package com.topone.projet_integration.Repository;

import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    Manager findByEmail(String email);
}
