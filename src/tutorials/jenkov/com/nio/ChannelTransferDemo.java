/**   
 * @{#} ChannelTransferDemo.java by Hongji.Xu 2018年2月9日 下午9:15:05 
 * @Title:  ChannelTransferDemo.java   
 * @Package tutorials.jenkov.com.nio   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 
 * @ClassName: ChannelTransferDemo
 * @Description:The FileChannel.transferFrom() method transfers data from a
 *                  source channel into the FileChannel.
 * @author: 11H0075
 * @date: 2018年2月9日 下午9:15:34
 *
 */
public class ChannelTransferDemo {
    public static void main(String[] args) {
        /*
         * The parameters position and count, tell where in the destination file to
         * start writing (position), and how many bytes to transfer maximally (count).
         * If the source channel has fewer than count bytes, less is transfered.
         */
        RandomAccessFile fromFile = null;
        FileChannel fromChannel = null;
        RandomAccessFile toFile = null;
        FileChannel toChannel = null;
        try {
            fromFile = new RandomAccessFile("20180124.xls", "rw");
            fromChannel = fromFile.getChannel();

            toFile = new RandomAccessFile("toFile.xls", "rw");
            toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();
            System.out.println("file lenght = " + count);

            toChannel.transferFrom(fromChannel, position, count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fromChannel.close();
                fromFile.close();
                toChannel.close();
                toFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
