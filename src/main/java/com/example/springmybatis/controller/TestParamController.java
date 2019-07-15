package com.example.springmybatis.controller;

import com.example.springmybatis.controller.param.SecondParam;
import com.example.springmybatis.controller.param.TestParam;
import com.sun.tracing.dtrace.ModuleAttributes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "AAA")
@RestController
public class TestParamController {


    @ApiOperation(value = "CCC")
    @PostMapping( value = "testParam" ,consumes={"application/x-www-form-urlencoded"},
            produces = {"application/x-www-form-urlencoded","application/json"},
            headers={"a=1"},params={"name=mike"} )
    public String postParam( HttpServletRequest request, @RequestBody MultiValueMap<String,String> json){
        String heaser = request.getHeader("Content-Type");

        System.out.println( "name->" + json.get("name"));
        return json.get("name").toString() ;
    }

    @ApiOperation("param test")
    @PostMapping(value = "/testEntityParam", consumes={"application/x-www-form-urlencoded"},
            produces = {"application/x-www-form-urlencoded","application/json"},
            headers={"a=1"},params={"name=mike"})
    public Object testEntityParam(TestParam testParam, SecondParam secondParam){

        String name = testParam.getName();
        return secondParam;
    }

}
