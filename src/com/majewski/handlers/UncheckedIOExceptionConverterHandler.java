package com.majewski.handlers;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionConverterHandler<T> extends DecoratedHandler<T> {

    public UncheckedIOExceptionConverterHandler(Handler<T> other) {
        super(other);
    }

    @Override
    public void handle(T t) throws UncheckedIOException {
        try {
            super.handle(t);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
