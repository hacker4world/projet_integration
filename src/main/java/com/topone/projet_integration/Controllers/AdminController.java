package com.topone.projet_integration.Controllers;

import com.topone.projet_integration.DTO.AccountRequestDTO;
import com.topone.projet_integration.Entities.User;
import com.topone.projet_integration.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint pour récupérer les demandes en attente
    @GetMapping("/pending-requests")
    public ResponseEntity<List<User>> getPendingRequests() {
        List<User> pendingRequests = adminService.getPendingRequests();
        return ResponseEntity.ok(pendingRequests);
    }

    // Endpoint pour traiter une demande
    @PostMapping("/process-request/{userId}")
    public ResponseEntity<String> processRequest(@PathVariable int userId, @RequestBody AccountRequestDTO requestDTO) {
        try {
            adminService.processAccountRequest(userId, requestDTO.getIsAccepted());
            return ResponseEntity.ok(requestDTO.getIsAccepted() ? "Demande acceptée" : "Demande refusée");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

