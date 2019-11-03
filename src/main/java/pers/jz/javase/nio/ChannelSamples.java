package pers.jz.javase.nio;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Jemmy Zhang on 2019/11/3.
 */
public class ChannelSamples {

    @Test
    public void testFileChannel() throws IOException {
        String filePath = this.getClass().getClassLoader().getResource("nio-data.txt").getPath();
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        FileChannel inChannel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        file.close();
    }
}
