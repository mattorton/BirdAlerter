package process;
import java.util.concurrent.LinkedBlockingQueue;
import domainobjects.IBirdSighting;
//import com.google.inject.ImplementedBy;

//@ImplementedBy(SightingsProcessor.class)
public interface ISightingsProcessor extends Runnable{
	void assignQueue(LinkedBlockingQueue<IBirdSighting> queue);
}