package com.example.springmybatis.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class CountDownLatchTest {

    private static final int num = 10 ;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch( num );
        CountDownLatchThread countDownLatchThread  = new CountDownLatchThread( countDownLatch );
        long start = System.currentTimeMillis();
        for (int i = 0 ; i < num  ; i ++){
            new Thread( countDownLatchThread ).start();
        }
        try{
            countDownLatch.await();     //挂起所有线程
        }catch (Exception e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("花费时间 ：" + (end - start));
    }
}

class CountDownLatchThread implements Runnable{

//    private final  AtomicReference<CountDownLatch> countDownLatchAtomicReference ;
    private final  CountDownLatch countDownLatch ;
    public CountDownLatchThread(CountDownLatch countDownLatch ){
        this.countDownLatch = countDownLatch;
//        this.countDownLatchAtomicReference = new AtomicReference<>(countDownLatch);
    }

    @Override
    public void run(){
        synchronized (this){
            System.out.println("thread : " + Thread.currentThread().getId() + " recent count --> " + countDownLatch.getCount() );
            countDownLatch.countDown();
        }
    }

}
