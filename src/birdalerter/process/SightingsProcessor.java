package birdalerter.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import birdalerter.domainobjects.IBirdSighting;
@Component
public class SightingsProcessor implements ISightingsProcessor{

	private LinkedBlockingQueue<IBirdSighting> queue;
	private List<ISightingVisitor> sightingVisitors = new ArrayList<ISightingVisitor>();
	
	public SightingsProcessor(){
	}
	
	/**
	 * Iterates the ISightingVisitor List, calling accept on the IBirdSighting
	 * and passing the configured visitors on each iteration
	 * @param sighting: The IBirdSighting concrete instance
	 */
	private void processSighting(IBirdSighting sighting){
		// Accept each configured sighting visitor
		for (ISightingVisitor visitor : sightingVisitors){
			sighting.accept(visitor);
		}
	}

	@Override
	public void run() {
		// Assign the ISightingVisitor(s) to use
		sightingVisitors.add(new SightingsVisitorImpl());
		
		// Obtain a reference to the Thread currently running
		Thread currentThread = Thread.currentThread();
		
		System.out.println(String.format("Started %s. Thread ID: %s", this.getClass().getName(), Thread.currentThread().getId()));
		
		// Continually attempt to process sightings until interrupted	
		while(!currentThread.isInterrupted())
		{
			try
			{
				processSighting(queue.take());
			}
			catch(InterruptedException e)
			{
				break;
			}			
		}

	}
	
	public void assignQueue(LinkedBlockingQueue<IBirdSighting> queue){
		this.queue = queue;
	}
}
