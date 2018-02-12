/**   
 * @{#} AsynchronousFileChannelDemo.java by Hongji.Xu 2018年2月10日 下午2:23:47 
 * @Title:  AsynchronousFileChannelDemo.java   
 * @Package tutorials.jenkov.com.nio   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsynchronousFileChannelDemo {

    public static void main(String[] args) throws Exception {

        Path path = Paths.get("test.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        AsynchronousFileChannel asynFileChennel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        // readViaCompletionHandler(asynFileChennel,buffer,position);
        // writeViaFuture(asynFileChennel, buffer, position);
        writeViaCompletionHandler(asynFileChennel, buffer, position);
    }

    @SuppressWarnings("unused")
    private static void readViaCompletionHandler(AsynchronousFileChannel asynFileChennel, ByteBuffer buffer,
            long position) throws IOException {

        asynFileChennel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[result];
                attachment.get(data);

                System.out.println(new String(data));

                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("read failed");
            }

        });
        // 给终端显示留一些时间，实际项目可以删除
        int cTime = 0;
        while (cTime < 5) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                ++cTime;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeViaCompletionHandler(AsynchronousFileChannel asynFileChennel, ByteBuffer buffer,
            long position) throws IOException {

        buffer.put("hello world.zzz,good.".getBytes());
        buffer.flip();
        asynFileChennel.write(buffer, position, null, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);
                System.out.println("write finished");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("read failed");
            }

        });
        // 给终端显示留一些时间，实际项目可以删除

        try {
            System.out.println("waiting for write.");
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unused")
    private static void readViaFuture(AsynchronousFileChannel asynFileChennel, ByteBuffer buffer, long position) {
        Future<Integer> readResult = asynFileChennel.read(buffer, position);
        while (!readResult.isDone()) {

        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }

    @SuppressWarnings("unused")
    private static void writeViaFuture(AsynchronousFileChannel asynFileChennel, ByteBuffer buffer, long position) {
        buffer = ByteBuffer.wrap("hello world.".getBytes());
        buffer.flip();
        Future<Integer> readResult = asynFileChennel.write(buffer, position);
        while (!readResult.isDone()) {

        }

        System.out.println("write finished.");

    }
}
