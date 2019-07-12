package com.majewski;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        handle(s);
    }

    private static void handle(Socket s) throws IOException {
        try (
                s;
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream()
        ) {
            System.out.println("Connected to socket");
            out.write("Welcome to my socket!\n".getBytes());
            int data;
            while ((data = in.read()) != -1) {
                out.write(transmogrify(data));
            }
        } finally {
            System.out.println("Disconnected from socket: " + s);
        }
    }

    private static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
