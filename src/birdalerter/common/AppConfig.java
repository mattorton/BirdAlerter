package birdalerter.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import birdalerter.process.ISightingsProcessor;
import birdalerter.process.SightingsProcessor;

@Configuration
@ComponentScan({"birdalerter.process", "birdalerter.common"})
public class AppConfig {
	@Bean
	@Scope("prototype")
	public ISightingsProcessor sightingsProcessor(){
		return new SightingsProcessor();
	}
}
