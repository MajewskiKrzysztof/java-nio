package com.majewski.server;

import com.majewski.server.handlers.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.majewski.server.util.Util.transmogrify;

class MultiThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler = new ThreadedHandler<>(
                new PrintingHandler<>(
                        new TransmogrifyHandler()
                )
        );

        while (true) {
            Socket s = ss.accept();
            handler.handle(s);
        }
    }

}
