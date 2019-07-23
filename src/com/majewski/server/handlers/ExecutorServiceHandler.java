package com.majewski.server.handlers;

import java.io.UncheckedIOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ExecutorServiceHandler<T> extends DecoratedHandler<T> {

    private ExecutorService threadPool;
    private Thread.UncaughtExceptionHandler exceptionHandler;

    public ExecutorServiceHandler(Handler<T> other, ExecutorService threadPool,
                                  Thread.UncaughtExceptionHandler exceptionHandler) {
        super(other);
        this.threadPool = threadPool;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void handle(T t) throws UncheckedIOException {
        threadPool.submit(new FutureTask<>(() -> {
            super.handle(t);
            return null; // callable instead of runnable
        }) { // prevent exceptions from being swallowed by ExecutorService
            @Override
            protected void setException(Throwable t) {
                exceptionHandler.uncaughtException(Thread.currentThread(), t);
            }
        });
    }
}
