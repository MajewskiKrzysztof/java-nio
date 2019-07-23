package com.majewski.handlers;

import java.io.UncheckedIOException;

public class ThreadedHandler<T> extends UncheckedIOExceptionConverterHandler<T> {

    public ThreadedHandler(Handler<T> other) {
        super(other);
    }

    @Override
    public void handle(T t) throws UncheckedIOException {
        new Thread(() -> super.handle(t)).start();
    }
}
