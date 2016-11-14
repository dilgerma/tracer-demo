package de.effectivetrainings;

import de.effectivetrainings.tracer.spring.config.EnableTracing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableTracing
@EnableScheduling
public class TracerDemoApplication {

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	public static void main(String[] args) {
		SpringApplication.run(TracerDemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}

	@Bean
	@ConditionalOnProperty(value = {"schedulingEnabled"}, havingValue = "true", matchIfMissing = true)
	public RequestScheduler requestScheduler() {
		return new RequestScheduler();
	}


}
