package domainobjects;

import java.util.Date;
import java.util.UUID;

import processing.ISightingVisitor;

public interface IBirdSighting{	
	UUID getUUID();
	void setUUID(UUID uuid);
	
	Date getDate();
	void setDate(Date date);
	
	String getName();
	void setName(String name);
	
	void accept(ISightingVisitor visitor);
}
