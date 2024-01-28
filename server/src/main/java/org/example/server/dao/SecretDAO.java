package org.example.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretDAO  extends JpaRepository<Secret, Integer> {
    Secret findSecretByHash(String hash);
    Secret findSecretById(Integer id);
}
