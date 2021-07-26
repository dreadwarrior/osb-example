package de.dreadlabs.osbexample.provisioning;

import de.dreadlabs.osbexample.catalog.CatalogService;
import de.dreadlabs.osbexample.provisioning.dto.ServiceInstance;
import de.dreadlabs.osbexample.provisioning.dto.ServiceInstanceRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class ProvisioningService {

    private final CatalogService catalog;

    private final HashMap<String, ServiceInstanceRequest> instances = new HashMap<>();

    public ProvisioningService(CatalogService catalog) {
        this.catalog = catalog;
    }

    public Mono<ServiceInstance> createServiceInstance(
            String instanceId,
            ServiceInstanceRequest serviceInstance
    ) {
        if (!catalog.hasService(serviceInstance.serviceId())) {
            return Mono.error(new UnknownService(serviceInstance.serviceId()));
        }
        if (!catalog.hasPlan(serviceInstance.planId())) {
            return Mono.error(new UnknownPlan(serviceInstance.planId()));
        }

        ServiceInstance instance = new ServiceInstance("http://localhost:8080/" + instanceId);

        if (instances.containsKey(instanceId) && instances.get(instanceId) == serviceInstance) {
            return Mono.error(new ServiceInstanceAlreadyExisting());
        }

        instances.put(instanceId, serviceInstance);

        return Mono.just(instance);
    }

    public Mono<Void> deleteServiceInstance(String instanceId) {
        if (!instances.containsKey(instanceId)) {
            return Mono.error(new ServiceInstanceDoesNotExist());
        }

        instances.remove(instanceId);

        return Mono.empty();
    }

    public Mono<ServiceInstance> getInstance(String instanceId) {
        return Mono.just(new ServiceInstance("http://localhost:8080/" + instanceId));
    }
}
