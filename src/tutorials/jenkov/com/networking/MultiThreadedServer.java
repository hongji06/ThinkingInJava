/**   
 * @{#} MultiThreadedServer.java by Hongji.Xu 2018年2月13日 上午11:36:11 
 * @Title:  MultiThreadedServer.java   
 * @Package tutorials.jenkov.com.networking   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerRunnable implements Runnable {
    private Socket clientSocket = null;

    public WorkerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();

            String responseDocumentString = "<html><body>" + Thread.currentThread().getName() + "Multithreaded Server: "
                    + time + "</body></html>";
            byte[] responseDocument = responseDocumentString.getBytes("UTF-8");

            String responseHeaderString = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html; charset=UTF-8\r\n"
                    + "Content-Length: " + responseDocument.length + "\r\n\r\n";
            byte[] responseHeader = responseHeaderString.getBytes("UTF-8");
            output.write(responseHeader);
            output.write(responseDocument);
            output.close();
            input.close();
            System.out.println(Thread.currentThread().getName() + " Request processed: " + time);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

public class MultiThreadedServer implements Runnable {

    private int serverPort = 8080;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    private ExecutorService exec = Executors.newFixedThreadPool(10);

    public MultiThreadedServer(int port) {
        serverPort = port;
    }

    public void run() {

        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("IOException:Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            // 啟動一個線程處理一個請求
            exec.execute(new WorkerRunnable(clientSocket));

        }

        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return isStopped;
    }

    public synchronized void stop() {
        isStopped = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(serverPort);
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }

    public static void main(String[] args) {
        MultiThreadedServer server = new MultiThreadedServer(9000);
        new Thread(server).start();
        System.out.println("Server started.");
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
