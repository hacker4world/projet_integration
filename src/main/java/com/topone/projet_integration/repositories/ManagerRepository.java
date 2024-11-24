package com.topone.projet_integration.repositories;

import com.topone.projet_integration.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    Optional<Manager> findByEmail(String email);

    @Query("SELECT m FROM Manager m WHERE m.accountAccepted = 0")
    List<Manager> findPendingManagerRequests();

}
