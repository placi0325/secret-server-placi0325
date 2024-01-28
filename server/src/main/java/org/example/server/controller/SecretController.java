package org.example.server.controller;

import org.example.server.dao.Secret;
import org.example.server.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SecretController {
    private SecretService secretService;
    @Autowired
    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping("/secret")
    public ResponseEntity<?> addSecret(@RequestBody SecretDTO secretDTO){
        String hash = secretService.addSecret(secretDTO.secretText(), secretDTO.expiresAt(), secretDTO.remainingViews());
        return new ResponseEntity<>(hash, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/secret/{hash}")
    public ResponseEntity<?> getSecretByHash(@PathVariable String hash){
        try {
            Secret secret = secretService.getSecretByHash(hash);
            return new ResponseEntity<>(secret.getSecretText(), HttpStatusCode.valueOf(200));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(405));
        }
    }
}
