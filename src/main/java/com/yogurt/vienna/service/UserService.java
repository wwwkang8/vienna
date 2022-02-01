package com.yogurt.vienna.service;

import com.yogurt.vienna.dto.UserDTO;
import com.yogurt.vienna.entity.User;
import com.yogurt.vienna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {

    Logger logger = Logger.getLogger("com.yogurt.vienna.controller.UserService");

    @Autowired
    UserRepository userRepository;

    public String subscribe(String email){

        // 중복검증
        if(isEmailExist(email) == true) {

            return "이미 사용하고 있는 이메일입니다.";
        }

        if(emailPwdValidation(email) == false){

            return "이메일 ,비밀번호 검증 필요";
        }

        String result = "";

        User user = new User();
        user.setEmail(email);
        user.setPwd(" ");

        try{
            userRepository.save(user);
            result = email + " 구독완료";
        }catch(Exception e){
            System.out.println("회원 정보 저장실패!");
            result = email + " 구독실패";
            e.printStackTrace();
        }

        return result;

    }


    private boolean isEmailExist(String email){

        //Email 기존재여부 확인
        User user = userRepository.findUserByEmail(email);

        if(user != null){
            System.out.println("사용자 기존재 : " + user.getEmail());

            return true;
        }else{
            System.out.println("이메일 사용 가능");

            return false;
        }
    }

    private boolean emailPwdValidation(String email){
        //이메일 @ 기호 포함여부 검증
        if(!email.contains("@")){
            System.out.println("@ 문자가 존재하지 않는 이메일");
            return false;
        }

        return true;
    }

    public UserDTO getUserInfo(Long id){

        User user = userRepository.findById(id);

        UserDTO userDTO = new UserDTO(user.getEmail(), user.getFirstName(), user.getPwd());

        return userDTO;
    }


}
