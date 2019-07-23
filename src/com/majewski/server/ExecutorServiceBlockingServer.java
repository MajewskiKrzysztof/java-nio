package com.majewski.server;

import com.majewski.handlers.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/*

RUN: telnet localhost 8080

1. Open two terminals
2. First one will get the sockets and responses
3. Second one will get responses only after the first one is disconnected. Requests can be send all time from many terminals
*/
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
