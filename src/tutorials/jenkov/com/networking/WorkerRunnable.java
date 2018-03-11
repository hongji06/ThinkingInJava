package tutorials.jenkov.com.networking;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

