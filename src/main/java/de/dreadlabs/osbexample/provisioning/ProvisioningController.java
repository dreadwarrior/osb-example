package de.dreadlabs.osbexample.provisioning;

import de.dreadlabs.osbexample.common.dto.EmptyResponse;
import de.dreadlabs.osbexample.provisioning.dto.ProvisioningResponse;
import de.dreadlabs.osbexample.provisioning.dto.ProvisioningRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/v2/service_instances")
public class ProvisioningController {

    private final ProvisioningService provisioningService;

    public ProvisioningController(
            ProvisioningService provisioningService
    ) {
        this.provisioningService = provisioningService;
    }

    @PutMapping(
            value = "/{instance_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ProvisioningResponse>> create(
            @PathVariable(name = "instance_id") String instanceId,
            @Valid @RequestBody ProvisioningRequest serviceInstance
    ) {
        return this.provisioningService.createServiceInstance(instanceId, serviceInstance)
                .map(it -> status(HttpStatus.CREATED).body(it))
                .onErrorResume(
                        ServiceInstanceExists.class,
                        e -> this.provisioningService.getInstance(instanceId).map(ResponseEntity::ok)
                );
    }

    @DeleteMapping(value = "/{instance_id}")
    public Mono<ResponseEntity<EmptyResponse>> delete(
            @PathVariable(name = "instance_id") String instanceId,
            @RequestParam(name = "service_id") String serviceId,
            @RequestParam(name = "plan_id") String planId
    ) {
        return this.provisioningService.deleteServiceInstance(instanceId).map(it -> ok(new EmptyResponse()));
    }
}
