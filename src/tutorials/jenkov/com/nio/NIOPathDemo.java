/**   
 * @{#} NIOPathDemo.java by Hongji.Xu 2018年2月10日 下午12:33:24 
 * @Title:  NIOPathDemo.java   
 * @Package tutorials.jenkov.com.nio   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOPathDemo {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("test.txt");
        System.out.println(path.getNameCount());
        System.out.println(path.getFileName());
        System.out.println(path.getFileSystem());
        if (path.toFile().isDirectory()) {
            System.out.println("is directory");
            System.out.println(path.getRoot().getFileName());
            System.out.println(path.getParent().getFileName());
        } else {
            System.out.println("it's a file");
        }
        
        Path currentDir = Paths.get("E:\\JavaProject\\ThinkingInJava\\..\\sales");
        System.out.println(currentDir.toAbsolutePath());
        System.out.println(currentDir.normalize());

    }

}
