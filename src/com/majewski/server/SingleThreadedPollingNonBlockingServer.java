package com.majewski.server;

import com.majewski.server.handlers.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;

class SingleThreadedPollingNonBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);
        UncheckedIOExceptionConverterHandler<SocketChannel> handler = new UncheckedIOExceptionConverterHandler<>(
                new TransmogrifyChannelHandler());
        Collection<SocketChannel> sockets = new ArrayList<>();
        while (true) {
            SocketChannel sc = ssc.accept(); // almost always null
            if (sc != null) {
                sockets.add(sc);
                System.out.println("Connected to " + sc);
                sc.configureBlocking(false);
            }
            sockets.stream().filter(SocketChannel::isConnected).forEach(handler::handle);
            sockets.removeIf(socket -> !socket.isConnected());
        }
    }

}
