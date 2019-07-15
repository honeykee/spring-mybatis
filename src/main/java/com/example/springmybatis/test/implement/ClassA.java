package com.example.springmybatis.test.implement;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClassA {
    public int id;
    public String uname;
    public double num;

    public ClassA(int id, String uname,  double num) {
        this.id = id;
        this.uname = uname ;
        this.num = num;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", num=" + num +
                '}';
    }
}
