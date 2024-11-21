package com.topone.projet_integration.Controllers;

import com.topone.projet_integration.DTO.ApiResponseDto;
import com.topone.projet_integration.DTO.ModifyProfileDto;
import com.topone.projet_integration.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("update")
    public ResponseEntity<ApiResponseDto> modifyProfile(@RequestBody ModifyProfileDto modifyProfileDto) {
        return profileService.modifyProfile(modifyProfileDto);
    }



}
