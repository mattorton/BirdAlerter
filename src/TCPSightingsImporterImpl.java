import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TcpSightingsImporterImpl implements SightingsImporter{

	private ServerSocket listener;
	private Socket server;
	private int maxConnections;
	private List<BirdSighting> birdSightings;
	
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
			tcpCommunicator tcpComms;
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
			tcpComms = new tcpCommunicator(server);
			Thread runThread = new Thread(tcpComms);
			runThread.start();
		}
		
	}

	@Override
	public void beginMonitorring() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseMonitoring() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMonitoring() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignList(List<BirdSighting> birdSightings) {
		// Obtain a reference to the shared queue (one instantiated per SightingsImporter)
		this.birdSightings = birdSightings;
	}
}