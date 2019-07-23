package com.majewski.handlers;

import java.io.IOException;

// Decorator common abstract super class
public abstract class DecoratedHandler<T> implements Handler<T> {

    private final Handler<T> other;

    public DecoratedHandler(Handler<T> other) {
        this.other = other;
    }

    @Override
    public void handle(T t) throws IOException {
        other.handle(t);
    }
}
