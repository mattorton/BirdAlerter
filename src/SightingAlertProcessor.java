import java.util.List;

public interface SightingAlertProcessor{
	
	public void run();
	
	public void assignList(List<BirdSighting> birdSightings);
}
