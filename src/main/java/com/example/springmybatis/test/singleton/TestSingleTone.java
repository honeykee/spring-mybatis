package com.example.springmybatis.test.singleton;

public class TestSingleTone {

    public static void main(String[] args) {
//        SingleTonOne singleTonOne1 = SingleTonOne.getInstance();
//        SingleTonOne singleTonOne2 = SingleTonOne.getInstance();
//        System.out.println(singleTonOne1 == singleTonOne2);

//        SingleTonTwo singleTonTwo3 =  SingleTonTwo.getInstance();
//        SingleTonTwo singleTonTwo4 =  SingleTonTwo.getInstance();
//        System.out.println(singleTonTwo3 == singleTonTwo4 );

//        SingleTonThree singleTonThree5 = SingleTonThree.ONE;
//        SingleTonThree singleTonThree6 = SingleTonThree.ONE;
//
//        System.out.println(singleTonThree5 == singleTonThree6);

        SingleTonFour singleTonFour7 = SingleTonFour.getInstance();

        SingleTonFour singleTonFour8 = SingleTonFour.getInstance();


        System.out.println(singleTonFour7 == singleTonFour8 );


    }
}
