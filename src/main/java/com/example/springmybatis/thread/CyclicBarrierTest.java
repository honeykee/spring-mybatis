package com.example.springmybatis.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static final int num = 4;
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> System.out.println( num + "位牌友到齐，开始游戏...") );

        for (int i = 0; i < num ; i++) {
            CyclicBarrierThread cyclicBarrierThread = new CyclicBarrierThread(i+1,cyclicBarrier);
            new Thread(cyclicBarrierThread).start() ;
        }
    }
}


class CyclicBarrierThread implements Runnable{

    private final CyclicBarrier cyclicBarrier ;
    private int id;

    public CyclicBarrierThread (int id , CyclicBarrier cyclicBarrier ){
        this.cyclicBarrier = cyclicBarrier ;
        this.id = id;
    }

    @Override
    public void run(){
        System.out.println("第"+ id + "名牌友到了...");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
