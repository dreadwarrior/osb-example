package de.dreadlabs.osbexample.binding;

import de.dreadlabs.osbexample.binding.dto.BindingRequest;
import de.dreadlabs.osbexample.binding.dto.BindingResponse;
import de.dreadlabs.osbexample.common.dto.EmptyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/v2/service_instances/{instance_id}/service_bindings")
public class BindingController {


    private final BindingService bindingService;

    public BindingController(
            BindingService bindingService
    ) {
        this.bindingService = bindingService;
    }

    @PutMapping(
            value = "/{binding_id}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<BindingResponse>> create(
            @PathVariable(name = "instance_id") String instanceId,
            @PathVariable(name = "binding_id") String bindingId,
            @Valid @RequestBody BindingRequest request
    ) {
        return bindingService.createBinding(instanceId, bindingId, request)
                .map(it -> status(HttpStatus.CREATED).body(it))
                .onErrorResume(
                        BindingAlreadyExisting.class,
                        e -> bindingService.getBinding(instanceId)
                                .map(ResponseEntity::ok)
                );
    }

    @DeleteMapping(
            value = "/{binding_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<EmptyResponse>> delete(
            @PathVariable(name = "instance_id") String instanceId,
            @PathVariable(name = "binding_id") String bindingId,
            @RequestParam(name = "service_id") String serviceId,
            @RequestParam(name = "plan_id") String planId
    ) {
        return bindingService.deleteBinding(instanceId, bindingId).map(it -> ok(new EmptyResponse()));
    }
}
