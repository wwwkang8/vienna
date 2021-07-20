package com.yogurt.vienna.controller;

import com.yogurt.vienna.config.JwtTokenUtil;
import com.yogurt.vienna.entity.JwtRequest;
import com.yogurt.vienna.entity.JwtResponse;
import com.yogurt.vienna.entity.UserDTO;
import com.yogurt.vienna.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    /** 참고문서 : https://www.javainuse.com/spring/boot-jwt */

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping("/hello")
    public String firstPage(){
        return "helloWorld";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{

        /** 응답받은 JwtRequest에서 username, password를 추출하여 authenticate 함수 호출 */
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        /** username으로 DB에서 해당 user가 있는지 확인 */
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        /** userDetails로 토큰 생성 */
        final String token = jwtTokenUtil.generateToken(userDetails);

        /** 발행한 토큰을 HTTP 응답객체에 넣어서 리턴 */
        return ResponseEntity.ok(new JwtResponse(token));


    }

//    @RequestMapping(value="/register", method = RequestMethod.POST)
//    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception{
//        return ResponseEntity.ok(userDetailsService.save(user));
//    }

    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



}
