package com.yogurt.vienna.service;

import com.yogurt.vienna.entity.AptDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AptNewsService {

    public List<AptDTO> getAptData(){

        List<AptDTO> aptDTOList = new ArrayList<>();

        try{
            /* 부동산 API연결 로직*/



        }catch(Exception e){
            System.out.println("Error occur because of api connection to get real estate!");
            e.printStackTrace();
        }

        return aptDTOList;
    }

}
