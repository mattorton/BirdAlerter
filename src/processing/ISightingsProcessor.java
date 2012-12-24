package processing;
import java.util.concurrent.LinkedBlockingQueue;

import domainobjects.IBirdSighting;

public interface ISightingsProcessor extends Runnable{
	void assignQueue(LinkedBlockingQueue<IBirdSighting> queue);
}