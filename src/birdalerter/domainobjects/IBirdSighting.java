package birdalerter.domainobjects;

import java.util.Date;
import java.util.UUID;

import birdalerter.process.ISightingVisitor;


public interface IBirdSighting{	
	UUID getUUID();
	void setUUID(UUID uuid);
	
	Date getDate();
	void setDate(Date date);
	
	String getName();
	void setName(String name);
	
	void accept(ISightingVisitor visitor);
}
