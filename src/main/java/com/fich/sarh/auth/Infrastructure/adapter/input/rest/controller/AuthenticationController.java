package com.fich.sarh.auth.Infrastructure.adapter.input.rest.controller;

import com.fich.sarh.auth.Application.ports.output.persistence.UserDetailsPort;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.LoginRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserDetailsPort userDetailsService;

   Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping(value = "log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest userRequest){

          AuthResponse authResponse =  userDetailsService.loginUser(userRequest);

        logger.info("AUTORIZADO RESPONSE " + authResponse );


        return new ResponseEntity<>( authResponse, HttpStatus.OK);

    }

    @PostMapping("refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> request){
        logger.info("REFRESH TOKEN " + request.get("refreshToken"));
        return ResponseEntity.ok(userDetailsService.refreshToken(request.get("refreshToken")));
    }


}
