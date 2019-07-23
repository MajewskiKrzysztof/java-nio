package com.majewski.server;

import com.majewski.handlers.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.majewski.util.Util.transmogrify;

/*

RUN: telnet localhost 8080

1. Open two terminals
2. First one will get the sockets and responses
3. Second one will get responses only after the first one is disconnected. Requests can be send all time from many terminals
*/
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
