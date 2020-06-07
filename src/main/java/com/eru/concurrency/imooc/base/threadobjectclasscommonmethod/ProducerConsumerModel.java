package com.eru.concurrency.imooc.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.LinkedList;

/**
 * 模拟生产消费者模式
 * Created by eru on 2020/5/29.
 */
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();

        new Thread(new Producer(storage)).start();
        new Thread(new Consumer(storage)).start();
    }
}

@Slf4j
class Producer implements Runnable{

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements Runnable{

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

@Slf4j
class EventStorage{
    private final LinkedList<Date> storage;
    private final int maxSize;

    public EventStorage() {
        storage = new LinkedList<>();
        maxSize = 10;
    }

    public synchronized void put(){
        while (storage.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        log.info("仓储里有" + storage.size() + "个产品.");
        notify();
    }

    public synchronized void take(){
        while (storage.size() == 0){
            log.info("仓库空了, 待生产产品");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("拿到了" + storage.poll() + ". 现在仓库还剩" + storage.size());
        notify();
    }
}
