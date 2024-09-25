package com.example._th_umc_study.repository;

import com.example._th_umc_study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}