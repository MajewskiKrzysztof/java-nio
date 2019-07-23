package com.majewski.server.handlers;

import java.io.IOException;

public class PrintingHandler<T> extends DecoratedHandler<T> {

    public PrintingHandler(Handler<T> other) {
        super(other);
    }

    @Override
    public void handle(T t) throws IOException {
        System.out.println("Connected to " + t);
        try {
            super.handle(t);
        } finally {
            System.out.println("Disconnected from " + t);
        }
    }
}
