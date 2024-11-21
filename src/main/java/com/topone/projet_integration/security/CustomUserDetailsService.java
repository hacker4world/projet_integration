package com.topone.projet_integration.security;

import com.topone.projet_integration.Entities.Admin;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Entities.User;
import com.topone.projet_integration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        String role;

        if (user instanceof Employee) role = "ROLE_EMPLOYEE";
        else if (user instanceof Manager) role = "ROLE_MANAGER";
        else if (user instanceof Admin) role = "ROLE_ADMIN";
        else throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );

    }
}
