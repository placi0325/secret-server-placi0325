package org.example.server.controller;

import org.example.server.dao.Secret;
import org.example.server.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> response = new HashMap<>();

        String hash = secretService.addSecret(secretDTO.secretText(), secretDTO.expiresAt(), secretDTO.remainingViews());

        response.put("hash", hash);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/secret/{hash}")
    public ResponseEntity<?> getSecretByHash(@PathVariable String hash){
        Map<String, Object> response = new HashMap<>();

        try {
            Secret secret = secretService.getSecretByHash(hash);
            response.put("secret", secret.getSecretText());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        } catch (Exception e){
            System.out.println(e.getMessage());
            response.put("secret", e.getMessage());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        }
    }
}
