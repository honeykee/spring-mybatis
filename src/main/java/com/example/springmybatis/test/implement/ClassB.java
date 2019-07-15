package com.example.springmybatis.test.implement;

import lombok.ToString;



public class ClassB extends ClassA {

    public ClassB(int id, String uname,  double num) {
        super(id, uname,    num);
    }

    @Override
    public String toString() {
        return "ClassB{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", num=" + num +
                '}';
    }
}
