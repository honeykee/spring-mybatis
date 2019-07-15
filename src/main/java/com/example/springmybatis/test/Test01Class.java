package com.example.springmybatis.test;

public class Test01Class {
    private volatile String name ;
    private volatile String[] names ;

    public static void main(String[] args) {
        Integer integer = new Integer(2);
        integer = integer.parseInt("1");
        System.out.println(integer.intValue());
        System.out.println(integer);
    }

}
