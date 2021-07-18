package com.yogurt.vienna.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class JwtUserDetailsService implements UserDetailsService {

    /** 참고문서 : https://www.javainuse.com/spring/boot-jwt */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /** username이 javainuse와 일치하면 User객체 생 */
        if("javainuse".equals(username)){
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6"
                       성      , new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }



}
