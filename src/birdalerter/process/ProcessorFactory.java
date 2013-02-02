package birdalerter.process;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
public class ProcessorFactory {
	private ISightingsProcessor sightingsProcessor;
	
	@Resource
	@Required
	private void setSightingsProcessor(ISightingsProcessor sightingsProcessor){
		this.sightingsProcessor = sightingsProcessor;
	}

	public ISightingsProcessor getSightingsProcessor(){
		return this.sightingsProcessor;
	}
}