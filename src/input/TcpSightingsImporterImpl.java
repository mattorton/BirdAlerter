package input;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import domainobjects.IBirdSighting;

public class TcpSightingsImporterImpl implements ISightingsImporter{

	private ServerSocket listener;
	private Socket server;
	private int maxConnections;
	private List<IBirdSighting> birdSightings;
	
	public TcpSightingsImporterImpl(int port, int maxConnections) {
		try {
			listener = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.maxConnections = maxConnections;
	}

	@Override
	public void run()
	{
		int i = 0;
		while ((i++ < maxConnections) || (maxConnections == 0))
		{
			TcpDataHandler tcpComms;
			try
			{
				System.out.println(String.format("Started %s", this.getClass()));
				server = listener.accept();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tcpComms = new TcpDataHandler(server);
			Thread runThread = new Thread(tcpComms);
			runThread.start();
		}
		
	}

	@Override
	public void assignList(List<IBirdSighting> birdSightings) {
		// TODO Auto-generated method stub
		
	}
}
