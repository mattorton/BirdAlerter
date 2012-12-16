import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class tcpCommunicator implements Runnable{

	private Socket serverSocket;
	private String input, line;
	
	public tcpCommunicator(Socket server) {
		this.serverSocket = server;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
	      try {
	          DataInputStream in = new DataInputStream (serverSocket.getInputStream());
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