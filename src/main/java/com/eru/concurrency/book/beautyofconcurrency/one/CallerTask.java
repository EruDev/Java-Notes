package com.eru.concurrency.book.beautyofconcurrency.one;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 演示 FutureTask 用法
 * Created by eru on 2020/6/25.
 */
public class CallerTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        // 启动线程
        new Thread(futureTask).start();
        // 等待任务执行 返回结果
        String result = futureTask.get();
        System.out.println(result);
    }
}
