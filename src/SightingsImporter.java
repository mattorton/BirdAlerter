
public interface SightingsImporter extends Runnable {

	void beginMonitorring();
	
	void pauseMonitoring();
	
	void stopMonitoring();
}
