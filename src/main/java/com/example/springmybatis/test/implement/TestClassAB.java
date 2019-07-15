package com.example.springmybatis.test.implement;

public class TestClassAB {

    public static void main(String[] args) {
        ClassB classB = new ClassB(12,"zsc",123d );

        ClassA classA = classB ;
        classB = null ;
        System.out.println(classA.toString() );
//        System.out.println(classB.toString() );

    }
}
