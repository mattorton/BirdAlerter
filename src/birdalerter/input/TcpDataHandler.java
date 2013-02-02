package birdalerter.input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TcpDataHandler implements Runnable{

	private Socket serverSocket;
	private String input, line;
	
	public TcpDataHandler(Socket server) {
		this.serverSocket = server;
	}

	@Override
	public void run() {
	      try {
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
	          PrintStream out = new PrintStream(serverSocket.getOutputStream());

			while((line = in.readLine()) != null && !line.equals(".")) {
	            input=input + line;
	            out.println("I got:" + line);
	          }

	          // Now write to the client

	          System.out.println("Overall message is:" + input);
	          out.println("Overall message is:" + input);

	          serverSocket.close();
	        } catch (IOException ioe) {
	          System.out.println("IOException on socket listen: " + ioe);
	          ioe.printStackTrace();
	        }
	}
}