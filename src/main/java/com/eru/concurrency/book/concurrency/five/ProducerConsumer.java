package com.eru.concurrency.book.concurrency.five;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by eru on 2020/7/7.
 */
public class ProducerConsumer {
    static class FileCrawler implements Runnable{
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
            this.fileQueue = fileQueue;
            this.root = root;
            this.fileFilter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || fileFilter.accept(f);
                }
            };

        }

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] files = root.listFiles(fileFilter);
            for (File f: files){
                if (f.isDirectory()){
                    crawl(f);
                }else if (!alreadyIndexed(f)){
                    fileQueue.put(f);
                }
            }
        }

        private boolean alreadyIndexed(File f) {
            return false;
        }
    }

    static class Indexer implements Runnable{
        private final BlockingQueue<File> queue;

        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true){
                    indexFile(queue.take());
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        public void indexFile(File f){
            // ...
        }
    }

    private static final int BOUND = 10;
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void startIndexing(File[] roots){
        LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        };
        for (File f: roots){
            new Thread(new FileCrawler(queue, filter, f)).start();
        }

        for (int i=0;i<N_CONSUMERS; i++){
            new Thread(new Indexer(queue)).start();
        }
    }
}
