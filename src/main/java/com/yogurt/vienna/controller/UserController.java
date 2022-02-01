package com.yogurt.vienna.controller;

import com.yogurt.vienna.dto.UserDTO;
import com.yogurt.vienna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = Logger.getLogger("com.yogurt.vienna.controller.UserController");

    @Autowired
    UserService userService;

    @RequestMapping(value="/subscribe", method = RequestMethod.POST)
    public ModelAndView subscribe(HttpServletRequest httpServletRequest){

        String email = httpServletRequest.getParameter("email");

        String result = userService.subscribe(email);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/subscribeSuccess");
        mv.addObject("result", result);

        return mv;

    }

    @ResponseBody
    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable("id") Long id){

        UserDTO userDTO = userService.getUserInfo(id);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value="/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody @Valid UserDTO userDTO){



    }









}
