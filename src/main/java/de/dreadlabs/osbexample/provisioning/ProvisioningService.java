package de.dreadlabs.osbexample.provisioning;

import de.dreadlabs.osbexample.provisioning.dto.ServiceInstance;
import de.dreadlabs.osbexample.provisioning.dto.ServiceInstanceMetadata;
import de.dreadlabs.osbexample.provisioning.dto.ServiceInstanceRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class ProvisioningService {

    private final HashSet<String> instances = new HashSet<>();

    public ServiceInstance createServiceInstance(
            String instanceId,
            ServiceInstanceRequest serviceInstance
    ) throws MalformedURLException {
        HashMap<String, String> labels = new HashMap<>();
        labels.put("scope", "demo");

        instances.add(instanceId);

        return new ServiceInstance(
                new URL("http://localhost:8080/" + instanceId).toString(),
                null,
                new ServiceInstanceMetadata(labels)
        );
    }

    public Mono<Void> deleteServiceInstance(String instanceId) {
        if (!instances.contains(instanceId)) {
            return Mono.error(new ServiceInstanceDoesNotExist());
        }

        instances.remove(instanceId);

        return Mono.empty();
    }
}
