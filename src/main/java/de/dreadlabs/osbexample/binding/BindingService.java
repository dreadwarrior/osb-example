package de.dreadlabs.osbexample.binding;

import de.dreadlabs.osbexample.binding.dto.BindingRequest;
import de.dreadlabs.osbexample.binding.dto.BindingResponse;
import de.dreadlabs.osbexample.common.ExistingEntityAttributesMismatch;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class BindingService {

    private final HashMap<String, HashMap<String, BindingRequest>> bindings = new HashMap<>();

    public Mono<BindingResponse> createBinding(
            String instanceId,
            String bindingId,
            BindingRequest request
    ) {
        if (bindings.containsKey(instanceId) && bindings.get(instanceId).containsKey(bindingId) && bindings.get(instanceId).get(bindingId).equals(request)) {
            return Mono.error(new BindingExists());
        }

        if (bindings.containsKey(instanceId) && bindings.get(instanceId).containsKey(bindingId) && !bindings.get(instanceId).get(bindingId).equals(request)) {
            return Mono.error(ExistingEntityAttributesMismatch.binding(instanceId, bindingId));
        }

        if (!bindings.containsKey(instanceId)) {
            bindings.put(instanceId, new HashMap<>());
        }
        bindings.get(instanceId).put(bindingId, request);

        return Mono.just(new BindingResponse());
    }

    public Mono<BindingResponse> getBinding(String instanceId) {
        return Mono.just(new BindingResponse());
    }

    public Mono<Void> deleteBinding(String instanceId, String bindingId) {
        bindings.get(instanceId).remove(bindingId);

        return Mono.empty();
    }
}
