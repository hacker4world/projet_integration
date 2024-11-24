package com.topone.projet_integration.controllers;

import com.topone.projet_integration.dto.ApiResponseDto;
import com.topone.projet_integration.dto.ProfileResponseDto;
import com.topone.projet_integration.dto.UpdateProfileDto;
import com.topone.projet_integration.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("details/{userId}")
    public ResponseEntity<ApiResponseDto<ProfileResponseDto>> getProfileDetails(@PathVariable("userId") int id) {
        return profileService.getProfileDetails(id);
    }

    @PutMapping("update")
    public ResponseEntity<ApiResponseDto<String>> updateProfile(@RequestBody UpdateProfileDto updateProfileDto) {
        return profileService.updateProfile(updateProfileDto);
    }

}
