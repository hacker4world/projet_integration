package com.topone.projet_integration.repositories;

import com.topone.projet_integration.entities.Employee;
import com.topone.projet_integration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query("SELECT e FROM User e WHERE e.accountAccepted = 1")
    List<User> findAcceptedUserRequests();
}
