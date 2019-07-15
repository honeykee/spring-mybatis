package com.example.springmybatis.bi.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BiMapper {
    @Select("select name from poi where id=2292840 ")
    String getName();
}
