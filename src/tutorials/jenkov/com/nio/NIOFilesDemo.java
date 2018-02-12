/**   
 * @{#} NIOFilesDemo.java by Hongji.Xu 2018年2月10日 下午1:02:03 
 * @Title:  NIOFilesDemo.java   
 * @Package tutorials.jenkov.com.nio   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NIOFilesDemo {
    public static void main(String[] args) {
        Path sourceFile = Paths.get("test.txt");
        Path path = sourceFile;
        // exists
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("file exist");
        } else {
            System.out.println("file not exist");
        }

        // createDirectory
        Path dir = Paths.get("test");
        try {
            Path newPath = Files.createDirectory(dir);
            System.out.println(newPath);
        } catch (FileAlreadyExistsException e) {
            System.out.println("FileAlreadyExists");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // copy
        Path destFile = Paths.get("text.txt");
        try {
            Files.copy(sourceFile, destFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("copy ok");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // walkFileTree
         walkFileTreeInterface();
        
        //walkFileTreeFindFile
        walkFileTreeFindFile();

    }

    private static void walkFileTreeFindFile() {
        Path walkFileTreeFindFile = Paths.get("E:\\JavaProject\\ThinkingInJava");
        try {
            Files.walkFileTree(walkFileTreeFindFile, new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(file.endsWith("Stack.java")) {
                        System.out.println("found it:"+file);
                        return FileVisitResult.TERMINATE;
                    }
                    return super.visitFile(file, attrs);
                }
                
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void walkFileTreeInterface() {
        Path walkFileTree = Paths.get("E:\\JavaProject\\ThinkingInJava");
        try {
            Files.walkFileTree(walkFileTree, new FileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("preVisitDirectory:" + dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("visitFile:" + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println("visitFileFailed" + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("postVisitDirectory" + dir);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
