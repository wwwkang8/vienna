package com.yogurt.vienna.jpaTest;

import com.yogurt.vienna.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("User 레포지토리 테스트")
    public void saveUser(){
        userRepository.findAll();
    }

}
