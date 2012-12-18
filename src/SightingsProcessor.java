import java.util.List;

public interface SightingsProcessor extends Runnable{
	void assignList(List<BirdSighting> birdSightings);
}
