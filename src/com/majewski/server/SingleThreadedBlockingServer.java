package com.majewski.server;

import com.majewski.handlers.Handler;
import com.majewski.handlers.PrintingHandler;
import com.majewski.handlers.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*

RUN: telnet localhost 8080

1. Open two terminals
2. First one will get the sockets and responses
3. Second one will get responses only after the first one is disconnected. Requests can be send all time from many terminals
*/
class SingleThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        Handler<Socket> handler = new PrintingHandler<>(new TransmogrifyHandler());
        handler.handle(s);
    }
}
