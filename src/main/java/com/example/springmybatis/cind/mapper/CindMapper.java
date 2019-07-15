package com.example.springmybatis.cind.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface CindMapper {

    @Select("select name from public.test where id=1000 ")
    String getName();
}
