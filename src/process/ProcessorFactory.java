package process;

import org.springframework.beans.factory.annotation.Autowired;

public class ProcessorFactory {

	private ISightingsProcessor processor;
	
	public ProcessorFactory() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private void setSightingsProcessor(ISightingsProcessor processor){
		this.processor = processor;
	}

	public ISightingsProcessor getSightingsProcessor(){
		return processor;
	}

}
