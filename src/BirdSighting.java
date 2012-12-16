import java.util.Date;
import java.util.UUID;

public interface BirdSighting{	
	UUID getUUID();
	void setUUID(UUID uuid);
	
	Date getDate();
	void setDate(Date date);
	
	String getName();
	void setName(String name);
}
