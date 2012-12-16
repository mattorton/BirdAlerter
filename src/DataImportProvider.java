import java.util.ArrayList;
import java.util.List;

public class DataImportProvider {

	private static SightingsImporter[] sightingsImporters = new SightingsImporter[]{new TCPSightingsImporterImpl(4444, 20)};
	static DataImportProvider instance = new DataImportProvider();
	private List<Thread> runThreads = new ArrayList<Thread>();
	
	public static DataImportProvider getInstance(){
		
		if(instance == null){
			instance = new DataImportProvider();
		}
			
		return instance;
	}
	
	// Start each importer running in a new thread
	public void startImporters(){
		for (SightingsImporter importer : sightingsImporters) {
			Thread runThread = new Thread(importer);
			runThreads.add(runThread);
			// Start the thread
			runThreads.get(runThreads.size()-1).start();
		}
	}
	
	// Stop each of the spawned threads
	public void stopImporters(){
		for (Thread thread: runThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}