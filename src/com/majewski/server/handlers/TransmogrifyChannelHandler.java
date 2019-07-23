package com.majewski.server.handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static com.majewski.server.util.Util.transmogrify;

public class TransmogrifyChannelHandler implements Handler<SocketChannel> {

    @Override
    public void handle(SocketChannel sc) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(80);
        int read = sc.read(buf);
        if (read == -1) {
            sc.close();
            return;
        }
        if (read > 0) {
            transmogrify(buf);
            while (buf.hasRemaining()) {
                sc.write(buf);
            }
            // buf.compact(); // reset the buffer. No need if creating new ByteBuffer every time
            buf.clear();
        }
    }
}
