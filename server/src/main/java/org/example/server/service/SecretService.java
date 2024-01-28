package org.example.server.service;

import org.example.server.dao.Secret;
import org.example.server.dao.SecretDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class SecretService {
    private SecretDAO secretDAO;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    public SecretService(SecretDAO secretDAO) {
        this.secretDAO = secretDAO;
    }

    public Secret getSecretByHash(String hash){

        Secret secret = secretDAO.findSecretByHash(hash);
        if(secret == null){
            throw new RuntimeException("No secret by this hash.");
        }

        Integer remainingViews = secret.getRemainingViews();
        if(remainingViews <= 0){
            throw new RuntimeException("No more views for secret.");
        }
        secret.setRemainingViews(remainingViews-1);

        LocalDateTime expiresAt = secret.getExpiresAt();
        if(expiresAt!= null && !expiresAt.isAfter(LocalDateTime.now())){
            throw new RuntimeException("Secret expired.");
        }

        secretDAO.save(secret);
        return secret;
    }

    public String addSecret(String secretText, Integer expiresAt, Integer remainingViews){

        String hash = encoder.encode(secretText);
        String urlSafeHash = makeUrlSafe(hash); // Convert to URL-safe hash
        System.out.println(urlSafeHash);

        LocalDateTime expirationTime = null;
        if (expiresAt != 0) {
            expirationTime = LocalDateTime.now().plusMinutes(expiresAt);
        }

        Secret secret = Secret.builder()
                        .hash(urlSafeHash)
                        .secretText(secretText)
                        .createdAt(LocalDateTime.now())
                        .expiresAt(expirationTime)
                        .remainingViews(remainingViews)
                        .build();

        secretDAO.save(secret);
        return urlSafeHash;
    }

    private String makeUrlSafe(String hash) {
        byte[] hashBytes = hash.getBytes(StandardCharsets.UTF_8);
        String base64Encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        return base64Encoded.replace('_', '-').replace('/', '_'); // Replace URL-unsafe characters
    }
}
