package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLsToLocalFileDemo {
	public static void main(String[] args) throws IOException {
		URL url = new URL("file:/c:/Users/Administrator/Desktop/temp/index.html");

		URLConnection urlConnection = url.openConnection();
		InputStream input = urlConnection.getInputStream();

		int data = input.read();
		while(data != -1){
		    System.out.print((char) data);
		    data = input.read();
		}
		input.close();
	}
}
