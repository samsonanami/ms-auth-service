package com.felid.ms_auth_service.controllers;

import com.felid.ms_auth_service.models.dtos.LoginUserDto;
import com.felid.ms_auth_service.models.dtos.RegisterUserDto;
import com.felid.ms_auth_service.models.dtos.UserDto;
import com.felid.ms_auth_service.models.entities.User;
import com.felid.ms_auth_service.responses.LoginResponse;
import com.felid.ms_auth_service.services.AuthenticationService;
import com.felid.ms_auth_service.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        UserDto user = UserDto.builder()
                .email(authenticatedUser.getEmail())
                .fullName(authenticatedUser.getFullName())
                .userName(authenticatedUser.getUsername())
                .build();

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .user(user)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

}
