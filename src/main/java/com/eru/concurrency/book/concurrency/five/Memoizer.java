package com.eru.concurrency.book.concurrency.five;

import java.util.concurrent.*;

/**
 * Created by eru on 2020/7/7.
 */
public class Memoizer<A, V> implements Computable<A, V>{
    private final ConcurrentMap<A, Future<V>> cache =
            new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if (f == null){
                Callable<V> eval = new Callable<V>() {

                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null){
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            }catch (CancellationException e){
                cache.remove(arg, f);
            }catch (ExecutionException e){
                throw LaunderThrowable.launderThrowable(e.getCause());
            }
        }
    }
}
