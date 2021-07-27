package de.dreadlabs.osbexample.provisioning;

import de.dreadlabs.osbexample.catalog.CatalogService;
import de.dreadlabs.osbexample.common.ExistingEntityAttributesMismatch;
import de.dreadlabs.osbexample.common.UnknownEntity;
import de.dreadlabs.osbexample.provisioning.dto.ProvisioningRequest;
import de.dreadlabs.osbexample.provisioning.dto.ProvisioningResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class ProvisioningService {

    private final CatalogService catalog;

    private final HashMap<String, ProvisioningRequest> instances = new HashMap<>();

    public ProvisioningService(CatalogService catalog) {
        this.catalog = catalog;
    }

    public Mono<ProvisioningResponse> createServiceInstance(
            String instanceId,
            ProvisioningRequest request
    ) {
        if (!catalog.hasService(request.serviceId())) {
            return Mono.error(UnknownEntity.service(request.serviceId()));
        }
        if (!catalog.hasPlan(request.planId())) {
            return Mono.error(UnknownEntity.plan(request.planId()));
        }

        ProvisioningResponse instance = new ProvisioningResponse("http://localhost:8080/" + instanceId);

        if (instances.containsKey(instanceId) && instances.get(instanceId).equals(request)) {
            return Mono.error(new ServiceInstanceExists());
        }

        if (instances.containsKey(instanceId) && !instances.get(instanceId).equals(request)) {
            return Mono.error(ExistingEntityAttributesMismatch.provisioning(instanceId));
        }

        instances.put(instanceId, request);

        return Mono.just(instance);
    }

    public Mono<Void> deleteServiceInstance(String instanceId) {
        if (!instances.containsKey(instanceId)) {
            return Mono.error(new UnknownServiceInstance("Service instance '" + instanceId + "' does not exist."));
        }

        instances.remove(instanceId);

        return Mono.empty();
    }

    public Mono<ProvisioningResponse> getInstance(String instanceId) {
        return Mono.just(new ProvisioningResponse("http://localhost:8080/" + instanceId));
    }
}
