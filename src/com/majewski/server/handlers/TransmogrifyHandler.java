package com.majewski.server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static com.majewski.server.util.Util.transmogrify;

public class TransmogrifyHandler implements Handler<Socket> {

    @Override
    public void handle(Socket s) throws IOException {
        try (s; InputStream in = s.getInputStream(); OutputStream out = s.getOutputStream()) {
            out.write("Welcome to my socket!\n".getBytes());
            int data;
            while ((data = in.read()) != -1) {
                out.write(transmogrify(data));
            }
        }
    }
}
