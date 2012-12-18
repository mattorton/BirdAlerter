import java.util.List;

public abstract class SightingAlertProcessorImpl extends Thread implements SightingAlertProcessor {

	//private LinkedBlockingQueue<BirdSighting> sightings;
	private volatile boolean terminateRequested;
	private volatile List<BirdSighting> birdSightings;
	
	public SightingAlertProcessorImpl() {
		//this.sightings = sightings;
		start();
	}

	@Override
	public synchronized void run(){
		System.out.println(String.format("Starting thread for %s", this.getClass()));
		while(!terminateRequested){
			BirdSighting sighting;
			try {
				sighting = SightingsMonitorWrapper.sightings.take();
				processBirdSighting(sighting);
				System.out.println(String.format("Bird sighting was handled by %s. The bird spotted was a %s", this.getClass(), sighting.getName()));
			} catch (Exception e) {
				System.out.println("Shyat");
				Thread.currentThread().interrupt();
			}
		}
	}
		// To do, this will contain the logic to process the bird sighting
		// in concrete overridden implementations	
	private void processBirdSighting(BirdSighting sighting){
	}
	
	@Override
	public void assignList(List<BirdSighting> birdSightings){
		this.birdSightings = birdSightings;
	}
}
