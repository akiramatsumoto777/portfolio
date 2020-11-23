package com.akm.portfolio.domain.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akm.portfolio.domain.model.user.User;

public interface UserRepository extends JpaRepository<User, String> {
}