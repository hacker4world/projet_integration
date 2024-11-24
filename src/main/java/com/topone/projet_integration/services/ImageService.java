package com.topone.projet_integration.services;

import com.topone.projet_integration.dto.ApiResponseDto;
import com.topone.projet_integration.entities.User;
import com.topone.projet_integration.repositories.EmployeeRepository;
import com.topone.projet_integration.repositories.ManagerRepository;
import com.topone.projet_integration.repositories.UserRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private final UserRepository userRepository;

    @Autowired
    public ImageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<ApiResponseDto<String>> uploadImage(MultipartFile imageFile, int userId) throws IOException {

        // find account by id
        Optional<User> user = userRepository.findById(userId);

        // if account is not found, return a 404 error
        if (user.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404,
                        ResponseMessage.ACCOUNT_NOT_FOUND.toString(),
                        "Could not find user with id " + userId)
                );

        // converting the image to bytes
        byte[] imageBytes = imageFile.getBytes();

        User account = user.get();

        // adding the image bytes to the account object
        account.setImage(imageBytes);

        // saving the changes in the database
        userRepository.save(account);

        // return success message
        return ResponseEntity
                .ok(new ApiResponseDto<String>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "User image has been uploaded successfully"
                ));

    }

    public ResponseEntity<ApiResponseDto<byte[]>> getImage(int userId) throws IOException {

        // find user by id
        Optional<User> user = userRepository.findById(userId);

        // if user is not found, return a 404 error
        if (user.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString())
        );

        // get image bytes from the account object
        byte[] imageBytes = user.get().getImage();

        // return success message with image bytes
        return ResponseEntity.status(200).body(
                new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), imageBytes)
        );
    }


}
