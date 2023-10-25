package com.okay.appplatform.tools.app;


import java.util.concurrent.Callable;


/**
 * @author zhou
 * @date 2020/7/31
 */
public class RunThread {

    private static int taskSize;
    private static Callable callable;

    private RunThread() {
    }

    private RunThread(int t, Callable c) {
        taskSize = t;
        callable = c;
    }

//    public static RunThread getInstance() {
//
//        return RunThreadFactory.instance;
//    }

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Date dateTmp1 = new Date();
//
//        int taskSIze = 5;
//        int availableProcessor = Runtime.getRuntime().availableProcessors() * 2 + 1;
//        //创建一个线程池
//        ExecutorService pool = Executors.newFixedThreadPool(availableProcessor);
//        List<Future> list = new ArrayList<>();
//
//        for (int i = 0; i < taskSIze; i++) {
//            Callable c = new MyCallable(i + "");
//            //执行任务并获取Future对象
//            Future future = pool.submit(c);
//            // 单个查询返回的Future
////            System.out.println(">>>" + future.get().toString());
//            list.add(future);
//        }
////        pool.shutdown();
//
//        System.out.println("返回结果");
//        // 获取所有并发任务的运行结果
//
//
//        for (Future f : list) {
//            // 从Future对象上获取任务的返回值，并输出到控制台
//            try {
//                System.out.println("执行结果>>>" + f.get(5,TimeUnit.SECONDS).toString());
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }
//        }
//        Date dateTmp2 = new Date();
//        long time = dateTmp2.getTime() - dateTmp1.getTime();
//        System.out.println("totalTime>>>" + time);
//        System.out.println("可以的线程数>>>" + Runtime.getRuntime().availableProcessors());
//        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) pool);
//
//        while (true) {
//            System.out.println();
//
//            int queueSize = tpe.getQueue().size();
//            System.out.println("当前排队线程数：" + queueSize);
//
//            int activeCount = tpe.getActiveCount();
//            System.out.println("当前活动线程数：" + activeCount);
//
//            long completedTaskCount = tpe.getCompletedTaskCount();
//            System.out.println("执行完成线程数：" + completedTaskCount);
//
//            long taskCount = tpe.getTaskCount();
//            System.out.println("总线程数：" + taskCount);
//
//            Thread.sleep(3000);
//        }
//    }
//
//    public List startRun() throws ExecutionException, InterruptedException {
//        //如果是CPU密集型应用，则线程池大小设置为N+1
//        //如果是IO密集型应用，则线程池大小设置为2N+1
//        int availableProcessor = Runtime.getRuntime().availableProcessors() * 2 + 1;
//        //创建一个线程池
//        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
//        List<Future> list = new ArrayList<>();
//        for (int i = 0; i < taskSize; i++) {
//            Callable ca = callable;
//            //执行任务并获取Future对象
//            Future future = pool.submit(ca);
//            list.add(future);
//
//        }
//        pool.shutdown();
//        return list;
//    }
//
//    static class RunThreadFactory {
//        private final static RunThread instance = new RunThread();
//    }
//
//    static class MyCallable implements Callable {
//        private String taskNum;
//
//        MyCallable(String taskNum) {
//            this.taskNum = taskNum;
//        }
//
//
//        @Override
//        public Object call() throws Exception {
//            System.out.println(">>>" + taskNum + "任务启动");
//            Date dateTmp1 = new Date();
//            Thread.sleep(10000);
//            Date dateTmp2 = new Date();
//
//            long time = dateTmp2.getTime() - dateTmp1.getTime();
//            System.out.println(">>>" + taskNum + "任务终止");
//
//            return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
//        }
//    }


}
