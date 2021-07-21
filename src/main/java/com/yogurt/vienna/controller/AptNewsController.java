package com.yogurt.vienna.controller;

import com.yogurt.vienna.entity.AptDTO;
import com.yogurt.vienna.service.AptNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AptNewsController {

    @Autowired
    AptNewsService aptNewsService;

    @RequestMapping(value = "/apt/{user}", method = RequestMethod.GET)
    public ResponseEntity<?> aptNewsProcs(@PathVariable("user") String user){

        if(!user.trim().isEmpty()){
            //if user id is filled with url


        }


        List<AptDTO> aptDTOList = new ArrayList<>();
        aptDTOList = aptNewsService.getAptData();

        return ResponseEntity.ok(aptDTOList);
    }


}
