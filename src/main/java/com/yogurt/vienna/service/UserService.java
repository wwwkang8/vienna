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

        // 중복검증
        if(isEmailExist(email) == true) {

            return "이미 사용하고 있는 이메일입니다.";
        }

        if(emailPwdValidation(email, password) == false){

            return "이메일 ,비밀번호 검증 필요";
        }

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

    public boolean isEmailExist(String email){

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

    public boolean emailPwdValidation(String email, String pwd){

        //이메일 @ 기호 포함여부 검증
        if(!email.contains("@")){
            System.out.println("@ 문자가 존재하지 않는 이메일");
            return false;
        }

        //비밀번호는 6자리이상
        if(pwd.length() < 6){
            System.out.println("비밀번호는 6자리 이상이어야 합니다.");
            return false;
        }



        return true;
    }


}
