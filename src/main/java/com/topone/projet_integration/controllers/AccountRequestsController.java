package com.topone.projet_integration.controllers;

import com.topone.projet_integration.dto.ApiResponseDto;
import com.topone.projet_integration.dto.AccountRequestDto;
import com.topone.projet_integration.dto.UnacceptedEmployeesResponseDto;
import com.topone.projet_integration.dto.UnacceptedManagersResponseDto;
import com.topone.projet_integration.services.AccountRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account-requests")
public class AccountRequestsController {
    private final AccountRequestsService accountRequestsService;

    @Autowired
    public AccountRequestsController(AccountRequestsService accountRequestsService) {
        this.accountRequestsService = accountRequestsService;
    }

    @GetMapping("employees")
    public List<UnacceptedEmployeesResponseDto> getPendingEmployees() {
        return accountRequestsService.getPendingEmployees();
    }

    @GetMapping("managers")
    public List<UnacceptedManagersResponseDto> getPendingManagers() {
        return accountRequestsService.getPendingManagers();
    }

    @PostMapping("handle-employee")
    public ResponseEntity<ApiResponseDto<String>> handleEmployeeRequest(@RequestBody AccountRequestDto accountRequestDto) {
        return accountRequestsService.handleEmployeeRequest(accountRequestDto);
    }

    @PostMapping("handle-manager")
    public ResponseEntity<ApiResponseDto<String>> handleManagerRequest(@RequestBody AccountRequestDto accountRequestDto) {
        return accountRequestsService.handleManagerRequest(accountRequestDto);
    }


}
