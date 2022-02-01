package com.yogurt.vienna.dto;

import com.yogurt.vienna.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

    /** DAO에서 받아온 데이터를 담는 포맷 객체로서의 DTO */

    @NotBlank(message="이메일을 입력하세요.")
    @Email(message="이메일 형식을 확인하세요.")
    private String email;

    private String username;

    private String password;

    public static UserDTO from(User user){
        return new UserDTO(user.getEmail(), user.getFirstName(), user.getPwd());
    }

}
