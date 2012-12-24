package processing;

import java.util.concurrent.LinkedBlockingQueue;

import domainobjects.IBirdSighting;

public class SightingsProcessor implements ISightingsProcessor{

	private LinkedBlockingQueue<IBirdSighting> queue;
	
	SightingsProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignQueue(LinkedBlockingQueue<IBirdSighting> queue) {
		this.queue = queue;
	}

}
