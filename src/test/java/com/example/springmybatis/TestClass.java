package com.example.springmybatis;

import com.example.springmybatis.config.MyProps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestClass {

    @Test
    public void test(){
        String str = "2018-09-01 19:19:19";
        System.out.println(str.substring(0,10));

        List listA = new ArrayList();
        List listB  = new ArrayList() ;

        listA.add("AAA");
        listA.add("BBB");
        listA.add("AAA");
        System.out.println(listA);
        listB.addAll(listA) ;
        System.out.println(listB);
        listA.add("VVV");
        System.out.println(listB);

    }

    @Test
    public void test2(){
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
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
        countDownLatch.countDown();

        try {
            System.out.println( "future1 " + future1.get());
            System.out.println( "future2 " + future2.get());
            System.out.println( "future3 " + future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test02(){

        ThreadLocal<String> threadLocal_1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocal_2= new ThreadLocal<>();
        threadLocal_1.set("111");
        threadLocal_2.set("222");

        Thread thread1 = new Thread( () -> {
            threadLocal_1.set("AAA");
            threadLocal_2.set("BBB");
            System.out.println( "thread1-1" + threadLocal_1.get() );
            System.out.println( "thread1-2" + threadLocal_2.get() );
            threadLocal_1.remove();
            threadLocal_1.remove();
        });
        thread1.setName("thread1");
        thread1.start();

        Thread thread2 = new Thread( () -> {
            threadLocal_1.set("DDD");
            threadLocal_2.set("EEE");
            System.out.println( "thread2-1" + threadLocal_1.get() );
            System.out.println( "thread2-2" + threadLocal_2.get() );
        });
        thread2.start();

        Thread thread3 = new Thread( () -> {
            threadLocal_2.set("FFF");
            System.out.println( "thread3-1" + threadLocal_1.get() );
            System.out.println( "thread3-2" + threadLocal_2.get() );
        });
        thread3.start();
        System.out.println( "main-1" + threadLocal_1.get() );
        System.out.println( "main-1" + threadLocal_2.get() );

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            thread2.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread3.yield();

    }

    @Test
    public void test03(){
//        StringBuilder stringBuilder = new StringBuilder("aaa");
//        StringBuilder stringBuilder2 = new StringBuilder("aaa");
//        stringBuilder = new StringBuilder("bbb");
//        stringBuilder.append("vvv");
//        System.out.println("----");
//        String abc = "aaa";
//        String bbb = new String("BBB");
//        String ccc = new String(new char[]{'d','b'});

        String str1 = new String("ssssss");
        byte[] b = new byte[]{};
        b = str1.getBytes(Charset.forName("UTF-8"));
        System.out.println(b);
        String str = new String(b, Charset.forName("UTF-8"));
        System.out.println(str);

        System.out.println(3*0.1 );

        byte a = 127;
        byte c = 127;
//        b = a + b; // error : cannot convert from int to byte
        c += a;
        System.out.println(c);
    }


    @Test
    public void test04(){
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(3);
        list.add(4);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            if( iterator.next().equals( 1 ) ){
                iterator.remove();
            }
        }
        list.forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println(list);

        Map<String,String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        map.put("c","C");
        map.put("d","D");
        map.put("e","E");
        map.put("f","F");

        //方式一
        map.forEach((key, value) -> {
            System.out.println("key:"+key + " value:" +value);
        });
        //方式二
        Set<Map.Entry<String, String>> mapSet = map.entrySet();
        Iterator<Map.Entry<String, String>> mapSetIterator = mapSet.iterator();
        while(mapSetIterator.hasNext()){
            Map.Entry<String, String> entry = mapSetIterator.next();
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue() );
        }
        //方式三
        Set<String> keySet = map.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while(keySetIterator.hasNext()){
            String key = keySetIterator.next();
            System.out.println("key: " + key + " value: " + map.get(key));
        }

    }

    @Test
    public void test05(){
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
//        executor.execute(() -> {
//            System.out.println("=====");
//        });
//
//        Future<String> future =  executor.schedule(()->{return "@@@";},3,TimeUnit.SECONDS );
//        try {
//            System.out.println("future " +future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,10,TimeUnit.SECONDS,new LinkedBlockingQueue<>(2));
            threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy() ); //不固定，不抛错，丢弃当前将要加入队列的任务本身
//            threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy() ); //执行所有，不进入线程池执行，任务将由调用者线程去执行
//            threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy() ); //拒绝并抛错，丢弃最旧任务也不是简单的丢弃最旧的任务，而是有一些额外的处理。除了丢弃任务还可以直接抛出一个异常
//            threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy() ); //不固定，不报错，丢弃任务队列中最旧任务

        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("---" + Thread.currentThread().getId() );
                }
            });
        }


    }

    @Test
    public void test06(){
        Lock lock = new ReentrantLock();
        lock.tryLock();
        lock.lock();
        System.out.println(((ReentrantLock) lock).getHoldCount());
        lock.unlock();
        System.out.println(((ReentrantLock) lock).getHoldCount());
        lock.unlock();
        System.out.println(((ReentrantLock) lock).getHoldCount());

    }



}
