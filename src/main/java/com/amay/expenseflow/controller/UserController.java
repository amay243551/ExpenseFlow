package com.amay.expenseflow.controller;

import com.amay.expenseflow.dto.LoginRequestDTO;
import com.amay.expenseflow.dto.LoginResponseDTO;
import com.amay.expenseflow.dto.UserResponseDTO;
import com.amay.expenseflow.dto.UserSignupRequestDTO;
import com.amay.expenseflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(
            @Valid @RequestBody UserSignupRequestDTO request) {

        return new ResponseEntity<>(
                userService.registerUser(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(
                userService.login(request)
        );
    }
}