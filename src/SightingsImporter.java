import java.util.List;


public interface SightingsImporter extends Runnable {

	void assignList(List<BirdSighting> birdSightings);
	
	void beginMonitorring();
	
	void pauseMonitoring();
	
	void stopMonitoring();
}
