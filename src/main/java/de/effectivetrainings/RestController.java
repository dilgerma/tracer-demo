package de.effectivetrainings;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.port:8080}")
    private int port;

    @Autowired
    private Environment environment;

    private ResponseInfo request() {
        String targetUri = environment.getProperty("targetUri");
        if (targetUri != null) {
            final ResponseInfo forObject = restTemplate.getForObject(targetUri, ResponseInfo.class);
            return forObject;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{host}/{port}")
    public ResponseInfo target(@PathVariable("host") String host, @PathVariable("port") String port) {
        final String targetUri = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(host)
                .port(port)
                .toUriString();
        final ResponseInfo target = restTemplate.getForObject(targetUri, ResponseInfo.class);
        return target;
    }

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseInfo incoming(HttpServletRequest servletRequest) {
        final ResponseInfo responseInfo = new ResponseInfo("responseInfo", port, servletRequest.getHeader("X-CORRELATION-ID"), request());
        log.info("Request / Response: {}", responseInfo);
        return responseInfo;
    }
}
