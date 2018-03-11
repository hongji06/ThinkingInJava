package tutorials.jenkov.com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
