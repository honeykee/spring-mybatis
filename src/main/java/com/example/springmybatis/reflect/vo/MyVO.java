package com.example.springmybatis.reflect.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MyVO {
    private int id;
    private String uname;
    private String upass;
    private boolean status;
    public double num;
    public short numShort;
    public Date date;
}
