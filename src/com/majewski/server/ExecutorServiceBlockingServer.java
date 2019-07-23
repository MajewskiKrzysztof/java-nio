package com.majewski.server;

import com.majewski.handlers.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

class ExecutorServiceBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler = new ExecutorServiceHandler<>(new PrintingHandler<>(new TransmogrifyHandler()),
//                                                               Executors.newCachedThreadPool(), // Integer.MAX_VALUE number of threads can be created
                                                               Executors.newFixedThreadPool(10), // only 10 connections are available
                                                               (t, e) -> System.out.println(
                                                                       "uncaught: " + t + " error " + e));

        while (true) {
            Socket s = ss.accept();
            handler.handle(s);
        }
    }

}
