package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.adProvider.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

}
