package com.user.service;

import com.user.config.JwtService;
import com.user.model.AuthenticationRequest;
import com.user.model.AuthenticationResponse;
import com.user.model.RegisterRequest;
import com.user.security.Role;
import com.user.security.User;
import com.user.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository  userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .emailId(registerRequest.getEmail())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .jwt(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),authenticationRequest.getPassword()));

        var user = userRepository.findByEmailId(authenticationRequest.getUserName())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .jwt(jwtToken)
                .build();

    }
}
