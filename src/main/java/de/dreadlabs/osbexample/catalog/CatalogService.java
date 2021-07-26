package de.dreadlabs.osbexample.catalog;

import de.dreadlabs.osbexample.catalog.dto.Catalog;
import de.dreadlabs.osbexample.catalog.dto.ServiceOffering;
import de.dreadlabs.osbexample.catalog.dto.ServicePlan;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Component
public class CatalogService {

    HashSet<ServicePlan> plans = new HashSet<>();

    HashSet<ServiceOffering> serviceOfferings = new HashSet<>();

    public CatalogService() {
        plans.add(
                new ServicePlan(
                        UUID.randomUUID().toString(),
                        "free-plan",
                        "Free plan with reduced QoS."
                )
        );


        serviceOfferings.add(
                new ServiceOffering(
                        UUID.randomUUID().toString(),
                        "in-memory-database",
                        "Provides an im-memory database.",
                        true,
                        plans
                )
        );
        serviceOfferings.add(
                new ServiceOffering(
                        UUID.randomUUID().toString(),
                        "hello-echo",
                        "Provides a service echoing your greeting.",
                        true,
                        plans
                )
        );
    }

    public Catalog list() {
        return new Catalog(serviceOfferings);
    }

    public boolean hasService(String serviceId) {
        return serviceOfferings.stream().anyMatch(it -> it.id().equals(serviceId));
    }

    public boolean hasPlan(String planId) {
        return plans.stream().anyMatch(it -> it.id().equals(planId));
    }
}
