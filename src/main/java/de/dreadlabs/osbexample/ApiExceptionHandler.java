package de.dreadlabs.osbexample;

import de.dreadlabs.osbexample.provisioning.ServiceBrokerError;
import de.dreadlabs.osbexample.provisioning.UnknownPlan;
import de.dreadlabs.osbexample.provisioning.UnknownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UnknownService.class)
    public Mono<ResponseEntity<ServiceBrokerError>> handleUnknownService(UnknownService exception) {
        return Mono.just(
                ResponseEntity.badRequest()
                        .body(new ServiceBrokerError("UnkownService", exception.getMessage()))
        );
    }

    @ExceptionHandler(UnknownPlan.class)
    public Mono<ResponseEntity<ServiceBrokerError>> handleUnknownPlan(UnknownPlan exception) {
        return Mono.just(
                ResponseEntity.badRequest()
                        .body(new ServiceBrokerError("UnknownPlan", exception.getMessage()))
        );
    }

}
