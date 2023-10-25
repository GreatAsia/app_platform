package com.okay.appplatform.tools.app;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.okay.appplatform.tools.MyThreadPool.getThreadPool;

/**
 * @author zhou
 * @date 2021/1/12
 */
public class CableFuture {

    private final static Logger logger = LoggerFactory.getLogger(CableFuture.class);


    public static void main(String[] args) {

        int availableProcessor = Runtime.getRuntime().availableProcessors() * 2 + 1;
        //创建一个线程池
//        ExecutorService pool = Executors.newFixedThreadPool(availableProcessor);

        ExecutorService pool = getThreadPool();
        CompletableFuture<Long> future = new CompletableFuture<Long>();
        List<CompletableFuture<Long>> list = new ArrayList<>();
        List<Long> resultList = new ArrayList<>();

        int j = 8;
        for (; j < 10; j++) {
            int x = j * 1000;
            future = CompletableFuture.supplyAsync(() -> {
                logger.info("线程号为*====="+ x + "==" + Thread.currentThread().getId());
                try {
                    Thread.sleep(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getId();

            }, pool).whenComplete((r, e) -> {
//            whenComplete第一个参数是结果，第二个参数是异常，他可以感知异常，无法返回默认数据
                resultList.add(r);
                logger.info(Thread.currentThread().getId() + " 执行完毕，结果是---" + r + "异常是----" + e);
            }).exceptionally(u -> {
//            exceptionally只有一个参数是异常类型，他可以感知异常，同时返回默认数据10
                return 10L;
//            handler既可以感知异常，也可以返回默认数据，是whenComplete和exceptionally的结合
            }).handle((r, e) -> {
                if (r != null) {
                    return r;
                }
                if (e != null) {
                    return 0L;
                }
                return 0L;
            });
            list.add(future);
        }
//        CompletableFuture<Void> result = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));
//        try {
//            result.get(5,TimeUnit.SECONDS);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            result.cancel(true);
//        } catch (ExecutionException e) {
//            result.cancel(true);
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            result.cancel(true);
//            e.printStackTrace();
//        }
        logger.info("resultList==" + JSONObject.toJSONString(resultList));
        pool.shutdown();


        for (CompletableFuture<Long> future1 : list) {
            try {
                try {
                    logger.info("x==" + future1.get(5, TimeUnit.SECONDS));
                } catch (TimeoutException e) {
                    future1.cancel(true);
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                future1.cancel(true);
            } catch (ExecutionException e) {
                e.printStackTrace();
                future1.cancel(true);
            }
        }
    }

    //无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });

        future.get();
    }

    //有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = "+time);
    }
}
