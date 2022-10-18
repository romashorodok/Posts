package com.example.server.repository;

import com.example.server.model.RefreshToken;
import com.example.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Query("select r from refreshtoken r where r.token = ?1")
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
