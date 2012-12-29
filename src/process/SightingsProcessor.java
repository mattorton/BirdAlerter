package process;

import java.util.concurrent.LinkedBlockingQueue;

import domainobjects.IBirdSighting;

public class SightingsProcessor implements ISightingsProcessor{

	private LinkedBlockingQueue<IBirdSighting> queue;

	@Override
	public void run() {
		try {
			while(true)
			{
				processSighting(queue.take());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void assignQueue(LinkedBlockingQueue<IBirdSighting> queue) {
		this.queue = queue;
	}
	
	private void processSighting(IBirdSighting sighting){
		// For now we will raise an Alert for every sighting
		sighting.accept(new SightingsVisitorImpl());
	}

}
