package de.dreadlabs.osbexample;

import de.dreadlabs.osbexample.common.ExistingEntityAttributesMismatch;
import de.dreadlabs.osbexample.common.dto.ServiceBrokerErrorResponse;
import de.dreadlabs.osbexample.common.UnknownEntity;
import de.dreadlabs.osbexample.provisioning.UnknownServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(UnknownEntity.class)
    public Mono<ResponseEntity<ServiceBrokerErrorResponse>> handle(UnknownEntity exception) {
        return Mono.just(
                ResponseEntity.badRequest()
                        .body(new ServiceBrokerErrorResponse("UnknownEntity", exception.getMessage()))
        );
    }

    @ExceptionHandler(ExistingEntityAttributesMismatch.class)
    public Mono<ResponseEntity<ServiceBrokerErrorResponse>> handle(ExistingEntityAttributesMismatch exception) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ServiceBrokerErrorResponse("ExistingEntityAttributesMismatch", exception.getMessage()))
        );
    }

    @ExceptionHandler(UnknownServiceInstance.class)
    public Mono<ResponseEntity<ServiceBrokerErrorResponse>> handle(UnknownServiceInstance exception) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.GONE)
                        .body(new ServiceBrokerErrorResponse("ServiceInstanceDoesNotExist", exception.getMessage()))
        );
    }
}
