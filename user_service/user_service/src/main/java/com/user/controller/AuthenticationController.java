package com.user.controller;

import com.user.model.AuthenticationRequest;
import com.user.model.AuthenticationResponse;
import com.user.model.RegisterRequest;
import com.user.service.AuthenticationService;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest)
            throws Exception {

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String,String> kafkaTemplate){
        return args -> {
          for(int i = 0 ; i < 10 ; i ++){
              kafkaTemplate.send("consumerTopic","Hello Kafka");
          }
        };
    }


}
