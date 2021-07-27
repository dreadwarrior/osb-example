package de.dreadlabs.osbexample;

import de.dreadlabs.osbexample.binding.BindingAlreadyExistingAttributesMismatch;
import de.dreadlabs.osbexample.common.ServiceBrokerError;
import de.dreadlabs.osbexample.provisioning.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApiExceptionControllerAdvice {

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

    @ExceptionHandler(ServiceInstanceAlreadyExistingAttributesMismatch.class)
    public Mono<ResponseEntity<ServiceBrokerError>> handle(ServiceInstanceAlreadyExistingAttributesMismatch exception) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ServiceBrokerError("ServiceInstanceExistWithDifferentAttributesThanRequested", exception.getMessage()))
        );
    }

    @ExceptionHandler(BindingAlreadyExistingAttributesMismatch.class)
    public Mono<ResponseEntity<ServiceBrokerError>> handle(BindingAlreadyExistingAttributesMismatch exception) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ServiceBrokerError("BindingAlreadyExistWithDifferentAttributesThanRequested", exception.getMessage()))
        );
    }

    @ExceptionHandler(ServiceInstanceDoesNotExist.class)
    public Mono<ResponseEntity<ServiceBrokerError>> handle(ServiceInstanceDoesNotExist exception) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.GONE)
                        .body(new ServiceBrokerError("ServiceInstanceDoesNotExist", exception.getMessage()))
        );
    }
}
