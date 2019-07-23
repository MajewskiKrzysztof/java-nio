package com.majewski.server.handlers;

import java.io.IOException;

// Decorator - this adds new functionalities to existing objects
public interface Handler<T> {

    void handle(T t) throws IOException;

}
