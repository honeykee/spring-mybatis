package com.example.springmybatis.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final int num  = 4;
    private static Semaphore semaphore = new Semaphore( 1 );
    public static void main(String[] args) {
        for (int i = 0; i < num ; i++) {
            new Thread(new SemaphoreThread(i+1 , semaphore  )).start();
        }
    }
}


class SemaphoreThread implements Runnable{

    private Semaphore semaphore ;
    private int id ;

    public SemaphoreThread(int id ,Semaphore semaphore ){
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run(){
        try{
            semaphore.acquire();
            System.out.println("第"+ id + "位车友进入停车场...");
            Thread.sleep(5000);

        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
        semaphore.release();
        System.out.println("第"+ id + "位车友开出停车场...");
    }
}
