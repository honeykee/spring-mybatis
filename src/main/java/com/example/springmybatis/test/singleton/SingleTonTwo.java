package com.example.springmybatis.test.singleton;

public class SingleTonTwo {

    private static SingleTonTwo instance;

    private SingleTonTwo(){}

    public static SingleTonTwo getInstance(){
        synchronized (SingleTonTwo.class){
            if (instance==null){
                synchronized (SingleTonTwo.class){
                    instance = new SingleTonTwo();
                }
            }
        }
        return instance;
    }

}
