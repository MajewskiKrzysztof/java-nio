package com.majewski.server;

import java.io.IOException;
import java.net.Socket;

/*
DDoS example.
It should throw OOMException
 */
public class NastyChump {
    public static void main(String[] args) throws InterruptedException, IOException {
        Socket[] sockets = new Socket[3000];
        for (int i = 0; i < sockets.length; i++) {
            sockets[i] = new Socket("localhost", 8080);
        }
        Thread.sleep(100_000);
    }
}
