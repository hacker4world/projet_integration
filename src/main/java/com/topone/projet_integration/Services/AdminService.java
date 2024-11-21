package com.topone.projet_integration.Services;

import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Entities.User;
import com.topone.projet_integration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    // Récupérer les demandes en attente (employés et managers)
    public List<User> getPendingRequests() {
        List<User> pendingRequests = new ArrayList<>();
        pendingRequests.addAll(userRepository.findPendingEmployeeRequests());
        pendingRequests.addAll(userRepository.findPendingManagerRequests());
        return pendingRequests;
    }

    // Accepter ou refuser une demande
    public void processAccountRequest(int userId, boolean isAccepted) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if (user instanceof Employee employee) {
            employee.setAccount_accepted(isAccepted ? 1 : -1);
            userRepository.save(employee);
        } else if (user instanceof Manager manager) {
            manager.setAccount_accepted(isAccepted ? 1 : -1);
            userRepository.save(manager);
        } else {
            throw new IllegalStateException("Type d'utilisateur inconnu ou non pris en charge");
        }
    }
}

