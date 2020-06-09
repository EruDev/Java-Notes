package com.eru.concurrency.geektime.features.semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * Semaphore Example
 * acquire() 为原子操作 保证只有一个线程能使count减为0 则其他线程阻塞等待, release()+1后得以执行
 * Created by eru on 2020/6/9.
 */
public class SemaphoreEx {

    static class MutexSemaphore{
        private int count;
        /** permits=1 表示只有一个线程能进入临界区 */
        private Semaphore semaphore = new Semaphore(1);

        /**
         * 用信号量保证互斥
         * @throws InterruptedException
         */
        public void addOne() throws InterruptedException {
            semaphore.acquire();
            try {
                count+=1;
            }finally {
                semaphore.release();
            }
        }
    }

    /**
     * 实现限流器
     * @param <T>
     * @param <R>
     */
    static class ObjPool<T, R>{
        final List<T> pool;

        /**
         * 用信号量实现限流器
         */
        final Semaphore semaphore;

        /**
         * 构造函数
         * @param size size
         * @param t t
         */
        public ObjPool(int size, T t) {
            pool = new Vector<T>();
            for (int i = 0; i < size; i++) {
                pool.add(t);
            }
            semaphore = new Semaphore(size);
        }

        /**
         * 利用对象池的对, 调用func
         * @param func func
         * @return R
         * @throws InterruptedException
         */
        R exec(Function<T, R> func) throws InterruptedException {
            T t = null;
            semaphore.acquire();
            try {
                t = pool.remove(0);
                return func.apply(t);
            }finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建对象池
        ObjPool<Object, String> objPool = new ObjPool<>(10, "Worker");
        // 通过对象池获取 t 之后执行
        objPool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
    }
}
