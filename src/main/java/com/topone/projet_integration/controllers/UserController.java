package com.topone.projet_integration.controllers;

import com.topone.projet_integration.dto.updatedUser;
import com.topone.projet_integration.entities.User;
import com.topone.projet_integration.services.AccountRequestsService;
import com.topone.projet_integration.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("list")
public class UserController {
    private final UserService userService;

    private final AccountRequestsService accountRequestsService;


    public UserController(UserService userService, AccountRequestsService accountRequestsService) {
        this.userService = userService;
        this.accountRequestsService = accountRequestsService;
    }
    @GetMapping("AllUsersAccepted")
    public List<User>getAllUsers(){
        return accountRequestsService.getAllUserAccepted();
    }
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody updatedUser updated) {
        return userService.updateUser(id,updated);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
      return   userService.deleteUser(id);

    }
}
