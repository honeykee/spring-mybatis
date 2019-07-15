package com.example.springmybatis.test.singleton;

public class SingleTonOne {

    private static SingleTonOne instance;

    private SingleTonOne(){
        instance = new SingleTonOne();
    }

    public static SingleTonOne getInstance(){
        return instance;
    }
}
