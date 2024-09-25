package com.example._th_umc_study.service;

import com.example._th_umc_study.entity.User;
import com.example._th_umc_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 새로운 사용자 저장
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // ID로 사용자 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}