package common;

import domainobjects.*;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Collections {
	
	public static LinkedBlockingQueue<IBirdSighting> sightings;
	public static LinkedBlockingQueue<BirdSightingAlert> sightingAlerts;
	private static Injector injector = Guice.createInjector();
	
	public static synchronized Injector getInjector(){
		return injector;
	}
}
