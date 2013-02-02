package birdalerter.process;

import java.util.concurrent.LinkedBlockingQueue;

import birdalerter.domainobjects.IBirdSighting;

public interface ISightingsProcessor extends Runnable{
	public void assignQueue(LinkedBlockingQueue<IBirdSighting> queue);
}