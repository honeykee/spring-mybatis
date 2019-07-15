package com.example.springmybatis.test.singleton;

public class SingleTonFour {

    private SingleTonFour(){}

    private static class SingleTonHolder{
        private final static SingleTonFour instance = new SingleTonFour();
    }

    public static SingleTonFour getInstance(){
        return SingleTonHolder.instance;
    }

}
