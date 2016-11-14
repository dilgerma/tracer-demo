package de.effectivetrainings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

public class RequestScheduler {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${targetUri:null}")
    private String targetUri;

    @Scheduled(fixedRateString = "2000")
    public void schedule() {
        try {
            restTemplate.getForObject(targetUri, ResponseInfo.class);
        } catch (Exception e) {
        }
    }
}
