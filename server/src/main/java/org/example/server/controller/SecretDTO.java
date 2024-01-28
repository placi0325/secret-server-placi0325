package org.example.server.controller;

public record SecretDTO(String secretText, Integer expiresAt, Integer remainingViews) {
}
