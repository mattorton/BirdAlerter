import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class BirdMonitorRabbit {
	
	private ConnectionFactory factory;
	private Channel channel;
	private Connection connection;
	private List<String> birdNames;
	private String queueNameString = "birdQueue";
	
	@SuppressWarnings("serial")
	public BirdMonitorRabbit()
	{
		birdNames = new ArrayList<String>(){{
			birdNames.add("Sparrow");
			birdNames.add("Jay");
			birdNames.add("Blackbird");
			birdNames.add("Robin");
			birdNames.add("Crow");
			birdNames.add("Red Kite");
			birdNames.add("Chaffinch");
			birdNames.add("Goldcrest");
			birdNames.add("Mallard");
			birdNames.add("Goldfinch");}};
	}
	
	public ConnectionFactory getFactory()
	{
		return factory;
	}
	
	public Channel getChannel()
	{
		return channel;
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public List<String> getBirdNames()
	{
		return birdNames;
	}
	
	public String getQueueName()
	{
		return queueNameString;
	}
	
	public void setupRabbitMQ(String host){
		factory = new ConnectionFactory();
		factory.setHost(host);
		try {
			try {
				connection = factory.newConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			channel = connection.createChannel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			channel.queueDeclare(queueNameString, false, false, false, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendToQueue(BirdSighting sighting){
		System.out.println(String.format("Sending %1$s", sighting.getName()));
		try {
			channel.basicPublish("", queueNameString, null, Serialiser.Serialise(sighting));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}