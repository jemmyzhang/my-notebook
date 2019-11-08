package pers.jz.javase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jemmy Zhang on 2019/11/7.
 */
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    dispatch(it.next());
                }
                selected.clear();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable runnable = (Runnable) (key.attachment());
        if (Objects.isNull(runnable)) {
            runnable.run();
        }
    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (Objects.isNull(socketChannel)) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    final class Handler implements Runnable {
        final SocketChannel socketChannel;
        final SelectionKey selectionKey;
        ByteBuffer input = ByteBuffer.allocate(64);
        ByteBuffer output = ByteBuffer.allocate(64);
        static final int READING = 0, SENDING = 1;
        int state = READING;

        public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {
            try {
                if (Objects.equals(READING, state)) {
                    read();
                }
                if (Objects.equals(SENDING, state)) {
                    send();
                }
            } catch (IOException ex) {
            }
        }

        void read() throws IOException {
            socketChannel.read(input);
            if (inputIsComplete()) {
                state = SENDING;
                selectionKey.interestOps(SelectionKey.OP_WRITE);
            }

        }

        void send() throws IOException {
            socketChannel.write(output);
            if (outputIsComplete()) {

            }
        }

        private boolean outputIsComplete() {
            return true;
        }

        private boolean inputIsComplete() {
            return true;
        }

        class Sender implements Runnable {

            @Override
            public void run() {
                try {
                    socketChannel.write(output);
                    if (outputIsComplete()) {
                        selectionKey.cancel();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
