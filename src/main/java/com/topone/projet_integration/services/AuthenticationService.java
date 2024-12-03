package com.topone.projet_integration.services;

import com.topone.projet_integration.dto.*;
import com.topone.projet_integration.entities.Admin;
import com.topone.projet_integration.entities.Employee;
import com.topone.projet_integration.entities.Manager;
import com.topone.projet_integration.entities.User;
import com.topone.projet_integration.repositories.UserRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import com.topone.projet_integration.security.JwtUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final AuthenticationProvider authenticationProvider;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, AuthenticationProvider authenticationProvider, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.authenticationProvider = authenticationProvider;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<ApiResponseDto<String>> signup(SignupDto signupDto) throws MessagingException {
        // check if email is already used
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());

        // if an account with given email exists, return a 400 error response
        if (user.isPresent()) return ResponseEntity.status(400).body(
                new ApiResponseDto<>(400, ResponseMessage.EMAIL_EXISTS.toString(), "Email address already used")
        );

        // encode the password before inserting in the database
        String encodedPassword = passwordEncoder.encode(signupDto.getPassword());

        // generate random email verification code for the new account
        String verificationCode = generateRandomCode();

        User newAccount;

        // if account is for employee, create a new employee instance
        if (signupDto.getAccountType().equals("employee")) {
            newAccount = new Employee(signupDto.getName(),
                    signupDto.getLastName(),
                    signupDto.getAge(),
                    signupDto.getAdress(),
                    signupDto.getEmail(),
                    signupDto.getRIB(),
                    encodedPassword,
                    signupDto.getRole_employer(),
                    false, verificationCode,
                    0);
        }
        // if account is for manager, create a new manager instance
        else if (signupDto.getAccountType().equals("manager")) {
            newAccount = new Manager(signupDto.getName(),
                    signupDto.getLastName(),
                    signupDto.getAge(),
                    signupDto.getAdress(),
                    signupDto.getEmail(),
                    signupDto.getRIB(),
                    encodedPassword,
                    signupDto.getGrade(),
                    false, verificationCode,
                    0);

            // if account type is invalid, send 400 error
        }
        else return ResponseEntity.status(400).body(
                new ApiResponseDto<>(400, ResponseMessage.INVALID_CREDENTIALS.toString(), "Invalid account type")
        );

        // save the new account in the database
        userRepository.save(newAccount);

        // send email with verification code to the user
        emailService.sendVerificationEmail(signupDto.getEmail(), signupDto.getName(), verificationCode);

        // return success response
        return ResponseEntity.status(200).body(
                new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), "Account created successfully")
        );

    }

    public ResponseEntity<LoginResponseDto> login(LoginDto loginDto) {

        try {
            // try logging in with given credentials
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            // get user details
            User user = userRepository.findByEmail(loginDto.getEmail()).get();

            // if the user is admin, generate token and send success message
            if (user instanceof Admin) {

                String token = jwtUtil.createToken(user.getEmail());

                return ResponseEntity.status(200).body(
                        new LoginResponseDto(
                                token, "Login successful", user.getId(), "ROLE_ADMIN"
                        ));
            }

            if (!user.isVerified_email()) {

                // generate a new verification code
                String verificationCode = generateRandomCode();

                // send the new verification code
                emailService.sendVerificationEmail(loginDto.getEmail(), user.getName(), verificationCode);

                // save the new code in the database
                user.setEmail_verification_code(verificationCode);
                userRepository.save(user);

                // return a 400 response to say email is not verified yet
                return ResponseEntity.status(400).body(
                        new LoginResponseDto("You did not verify your email yet, a new verification email has been sent")
                );

            } else {

                // if account is rejected, return a 400 response saying that account is rejected
                if (user.getAccountAccepted() == -1) return ResponseEntity.status(400).body(
                        new LoginResponseDto("Your account has been rejected")
                );
                // if account is not accepted yet, return a 400 response saying that account is pending validation
                else if (user.getAccountAccepted() == 0) return ResponseEntity.status(400).body(
                        new LoginResponseDto("Your account is still awaiting approval")
                );
                else {
                    // generate jwt for the user
                    String token = jwtUtil.createToken(user.getEmail());

                    // get the role of the logged-in user
                    String role = authentication.getAuthorities().iterator().next().getAuthority();

                    // return a success response with the token attached
                    return ResponseEntity.status(200).body(
                            new LoginResponseDto(token, "Login successful", user.getId(), role)
                    );
                }
            }

        } catch (Exception e) {
            // when the credentials are invalid, send a 401 error
            return ResponseEntity.status(401).body(
                    new LoginResponseDto("Invalid email or password")
            );
        }

    }

    public ResponseEntity<ApiResponseDto<String>> verifyEmail(VerifyEmailDto verifyEmailDto) {
        // get user by email
        Optional<User> user = userRepository.findByEmail(verifyEmailDto.getEmail());

        // if user does not exists return a 404 error
        if (user.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString(), "Email does not exist")
        );

        User account = user.get();

        if (account.isVerified_email()) return ResponseEntity.status(400).body(
                new ApiResponseDto<>(400, ResponseMessage.EMAIL_ALREADY_VERIFIED.toString(), "Email is already verified")
        );

        // if verification code is incorrect, return 401 error
        if (!verifyEmailDto.getCode().equals(account.getEmail_verification_code())) {
            return ResponseEntity.status(401).body(
                    new ApiResponseDto<>(401, ResponseMessage.INVALID_CREDENTIALS.toString(), "Verification code is incorrect")
            );
        }

        // if verification code is valid, verify the email and save in the database
        account.setVerified_email(true);
        userRepository.save(account);

        // return success message
        return ResponseEntity.status(200).body(
                new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), "Email successfully verified")
        );

    }

    public ResponseEntity<ApiResponseDto<String>> sendPasswordResetEmail(SendPasswordResetEmailDto sendPasswordResetEmailDto) throws MessagingException {

        // find the user by email
        Optional<User> user = userRepository.findByEmail(sendPasswordResetEmailDto.getEmail());

        // if the user is not found, return a 404 error
        if (user.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString(), "Email does not exist")
        );

        // generate a random password reset code
        String passwordResetCode = generateRandomCode();

        User account = user.get();

        // add the password reset code to the account
        account.setPasswordResetCode(passwordResetCode);

        // save the account changes in the database
        userRepository.save(account);

        // send email to the user containing the passwsord reset link
        emailService.sendPasswordResetEmail(account.getEmail(), account.getName(), passwordResetCode);

        // return a success message
        return ResponseEntity.status(200).body(
                new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), "An email containing password reset link has been sent to your email ")
        );
    }

    public ResponseEntity<ApiResponseDto<String>> resetPassword(ResetPasswordDto resetPasswordDto) {

        // find user by email
        Optional<User> user = userRepository.findByEmail(resetPasswordDto.getEmail());

        // if the user is not found, return a 404 error
        if (user.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString(), "Email does not exist")
        );

        User account = user.get();

        // if the provided reset code is incorrect, return a 401 error
        if (!account.getPasswordResetCode().equals(resetPasswordDto.getResetCode())) return ResponseEntity.status(401).body(
                new ApiResponseDto<>(401, ResponseMessage.INVALID_CREDENTIALS.toString(), "Invalid reset code")
        );

        // change the password to the new password
        account.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));

        // remove the password reset code from the account
        account.setPasswordResetCode("");

        // save account changes in the database
        userRepository.save(account);

        // return a success message
        return ResponseEntity.status(200).body(
                new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), "Password has been modified")
        );

    }

    // method that generates a random 5-digit code for verifying email
    private String generateRandomCode() {
        Random rand = new Random();
        int randomCode = 10000 + rand.nextInt(90000);
        return String.valueOf(randomCode);
    }




}
