
public class SightingAlertProcessorFactory {

	private static SightingAlertProcessorImpl[] sightingAlertProcessors = new SightingAlertProcessorImpl[]{ new RegionSightingAlertProcessor() };
	static SightingAlertProcessorFactory instance = new SightingAlertProcessorFactory();
	
//	private SightingAlertProcessorFactory(){
//		
//	}
	
	public static SightingAlertProcessorFactory getInstance(){
		
		if(instance == null){
			instance = new SightingAlertProcessorFactory();
		}
			
		return instance;
	}
	
	public SightingAlertProcessorImpl[] getAlertProcessors(){
		return sightingAlertProcessors;
	}
}