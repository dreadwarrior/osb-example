package de.dreadlabs.osbexample.provisioning;

import de.dreadlabs.osbexample.provisioning.dto.ServiceInstance;
import de.dreadlabs.osbexample.provisioning.dto.ServiceInstanceRequest;
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
            headers = {"X-Broker-API-Version=2.16"},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ServiceInstance>> create(
            @PathVariable(name = "instance_id") String instanceId,
            @Valid @RequestBody ServiceInstanceRequest serviceInstance
    ) {
        return this.provisioningService.createServiceInstance(instanceId, serviceInstance)
                .map(it -> status(HttpStatus.CREATED).body(it))
                .onErrorResume(
                        ServiceInstanceAlreadyExisting.class,
                        e -> this.provisioningService.getInstance(instanceId)
                                .map(ResponseEntity::ok)
                );
    }

    @DeleteMapping(
            value = "/{instance_id}",
            headers = {"X-Broker-API-Version=2.16"}
    )
    public Mono<ResponseEntity<String>> delete(
            @PathVariable(name = "instance_id") String instanceId,
            @RequestParam(name = "service_id") String serviceId,
            @RequestParam(name = "plan_id") String planId
    ) {
        return this.provisioningService.deleteServiceInstance(instanceId)
                .map(it -> ok(""))
                .onErrorReturn(ServiceInstanceDoesNotExist.class, status(HttpStatus.GONE).body(""));
    }
}
