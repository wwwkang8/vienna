package com.yogurt.vienna.service;

import com.yogurt.vienna.dto.UserDTO;
import com.yogurt.vienna.entity.User;
import com.yogurt.vienna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String register(String email, String password){

        User user = new User();
        user.setEmail(email);
        user.setPwd(password);
        try{
            userRepository.save(user);
        }catch(Exception e){
            System.out.println("회원 정보 저장실패!");
            e.printStackTrace();
        }


        return "회원가입완료";
    }


}
