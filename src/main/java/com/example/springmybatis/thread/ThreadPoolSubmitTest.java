package com.example.springmybatis.thread;

import java.util.concurrent.*;

public class ThreadPoolSubmitTest {
    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);


        Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        FutureTask future1 = new FutureTask( (Callable<String>) () -> { return "BBB"; } );
        FutureTask future2 = new FutureTask( new Callable<String>(){
            @Override
            public String call() throws Exception {
//                System.out.println(1/0);
                return "CCC";
            }
        } );
        Future future3 = fixedThreadPool.submit( future1 );
        fixedThreadPool.execute(future2);
        Future future4 = fixedThreadPool.submit( ()-> {return "abc";});

        countDownLatch.countDown();

        try {
            System.out.println( "future1 " + future1.get());
            System.out.println( "future2 " + future2.get());
            System.out.println( "future3 " + future3.get());
            System.out.println( "future4 " + future4.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


//*******************************************************************************
        ExecutorService pool = Executors.newFixedThreadPool(2);

        /**
         * execute(Runnable x) 没有返回值。可以执行任务，但无法判断任务是否成功完成。
         */
        pool.execute(new RunnableTest("Task1"));

        /**
         * submit(Runnable x) 返回一个future。可以用这个future来判断任务是否成功完成。请看下面：
         */
        Future future = pool.submit( new RunnableTest("Task2" ) );

        try {
            if(future.get()==null){//如果Future's get返回null，任务完成
                System.out.println("任务完成");
            }
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            //否则我们可以看看任务失败的原因是什么
            System.out.println(e.getCause().getMessage());
        }

    }


}

 class RunnableTest implements Runnable {

    private String taskName;

    public RunnableTest(final String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Inside "+taskName);
//        throw new RuntimeException("RuntimeException from inside " + taskName);
    }

}
