package com.topone.projet_integration.services;

import com.topone.projet_integration.dto.ApiResponseDto;
import com.topone.projet_integration.dto.ProfileResponseDto;
import com.topone.projet_integration.dto.UpdateProfileDto;
import com.topone.projet_integration.entities.Admin;
import com.topone.projet_integration.entities.Employee;
import com.topone.projet_integration.entities.Manager;
import com.topone.projet_integration.entities.User;
import com.topone.projet_integration.repositories.AdminRepository;
import com.topone.projet_integration.repositories.EmployeeRepository;
import com.topone.projet_integration.repositories.ManagerRepository;
import com.topone.projet_integration.repositories.UserRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {


    private final UserRepository userRepository;

    @Autowired
    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public ResponseEntity<ApiResponseDto<ProfileResponseDto>> getProfileDetails(int profileId) {

        // find user by id
        Optional<User> user = userRepository.findById(profileId);

        // if user is not found, return a 404 error
        if (user.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString())
        );

        User account = user.get();

       ProfileResponseDto profileData;

       // if account is an employee, create the profile data object with employee specific informations
       if (account instanceof Employee employee) {
           profileData = new ProfileResponseDto(
                account.getName(), account.getLastName(), account.getEmail(), account.getAge(), employee.getRole_employer(), 0, 0
           );
           // if account is a manager create the profile data object with manager specific informations
       } else if (account instanceof Manager manager) {
           profileData = new ProfileResponseDto(
                   account.getName(), account.getLastName(), account.getEmail(), account.getAge(), "", manager.getGrade(), 0
           );
           // if account is an admin, create the profile data object with admin specific informations
       } else {
           Admin admin = (Admin) account;
           profileData = new ProfileResponseDto(
                   account.getName(), account.getLastName(), account.getEmail(), account.getAge(), "", 0,  (double) admin.getExperience()
           );
       }

       // return success message with profile data
       return ResponseEntity.status(200).body(
               new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), profileData)
       );

   }

   public ResponseEntity<ApiResponseDto<String>> updateProfile(UpdateProfileDto updateProfileDto) {

        // find user by id
        Optional<User> user = userRepository.findById(updateProfileDto.getUserId());

        // if user is not found, return a 404 error
        if (user.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString())
        );

        User account = user.get();

        // update account with new informations
        account.setName(updateProfileDto.getFirstName());
        account.setLastName(updateProfileDto.getLastName());
        account.setAge(updateProfileDto.getAge());
        account.setAdress(updateProfileDto.getAdress());

        // save the changes in the database
        userRepository.save(account);

        // return a success message
        return ResponseEntity.status(200).body(
                new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), "Profile updated successfully")
        );

   }

}
