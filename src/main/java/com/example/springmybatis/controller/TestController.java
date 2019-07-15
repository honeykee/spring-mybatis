package com.example.springmybatis.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springmybatis.bi.mapper.BiMapper;
import com.example.springmybatis.cind.mapper.CindMapper;
import com.example.springmybatis.config.MyProps;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api
@Slf4j
@RestController
public class TestController {

    @Autowired
    BiMapper biMapper;

    @Autowired
    CindMapper cindMapper;





    @ApiOperation("getName")
    @GetMapping("/getName")
    public String getName(){
        String name1 = biMapper.getName();
        String name2 = cindMapper.getName();
        System.out.println( name1 + "____________________________" + name2 );

        return "789";
    }

    @GetMapping("/getName2")
    public void  testPostgres(){
        String name2 = cindMapper.getName();
        System.out.println("____________________________");
    }

    @PostMapping("/sendRSSI")
    public void getRSSI(HttpServletRequest request, @RequestBody MultiValueMap<String,String> json){ //@RequestBody JSONObject json
        Map map =request.getParameterMap();
        json.keySet();

        log.info( "1--->" + json.toString());
        System.out.println();
    }

    @PostMapping("/sendRSSI2")
    public void getRSSI2(HttpServletRequest request, @RequestBody JSONArray json){ //@RequestBody JSONObject json
        Map map =request.getParameterMap();

        log.info( "1--->" + json.toString());
        System.out.println();
    }

}
