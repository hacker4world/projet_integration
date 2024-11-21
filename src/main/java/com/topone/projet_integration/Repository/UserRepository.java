package com.topone.projet_integration.Repository;

import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query("SELECT e FROM Employee e WHERE e.account_accepted = 0")
    List<Employee> findPendingEmployeeRequests();

    @Query("SELECT m FROM Manager m WHERE m.account_accepted = 0")
    List<Manager> findPendingManagerRequests();
}
