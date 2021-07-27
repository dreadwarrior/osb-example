package de.dreadlabs.osbexample;

//import de.dreadlabs.osbexample.common.ServiceBrokerError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class OsbApiVersionWebFilter implements WebFilter {

    private final ApplicationConfigProperties applicationConfig;

    public OsbApiVersionWebFilter(ApplicationConfigProperties applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        if (!headers.containsKey("X-Broker-API-Version")) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "HTTP header 'X-Broker-API-Version' is missing.");

//            exchange.getResponse().setStatusCode(HttpStatus.PRECONDITION_FAILED);
//            return exchange.getResponse().writeWith(Mono.error(new OsbApiVersionMismatch("HTTP header 'X-Broker-API-Version' is missing.")));
        }

        if (!Objects.equals(headers.getFirst("X-Broker-API-Version"), applicationConfig.osbApiVersion())) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Only OSB API version '" + applicationConfig.osbApiVersion() + "' is supported.");

//            exchange.getResponse().setStatusCode(HttpStatus.PRECONDITION_FAILED);
//            return exchange.getResponse().writeWith(Mono.error(new OsbApiVersionMismatch("Only OSB API version '" + applicationConfig.osbApiVersion() + "' is supported.")));
        }

        return chain.filter(exchange);
    }
}
