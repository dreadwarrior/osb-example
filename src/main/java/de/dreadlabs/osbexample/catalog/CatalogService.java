package de.dreadlabs.osbexample.catalog;

import de.dreadlabs.osbexample.catalog.dto.Catalog;
import de.dreadlabs.osbexample.catalog.dto.ServiceOffering;
import de.dreadlabs.osbexample.catalog.dto.ServicePlan;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CatalogService {

    public Catalog list() {

        HashSet<ServicePlan> plans = new HashSet<>();

        plans.add(
                new ServicePlan(
                        "free",
                        "Free plan",
                        "Free plan with reduced QoS."
                )
        );

        HashSet<ServiceOffering> serviceOfferings = new HashSet<>();

        serviceOfferings.add(
                new ServiceOffering(
                        "In-Memory Database",
                        "in-memory-database",
                        "Provides an im-memory database.",
                        true,
                        plans
                )
        );
        serviceOfferings.add(
                new ServiceOffering(
                        "Hello echo",
                        "hello-echo",
                        "Provides a service echoing your greeting.",
                        true,
                        plans
                )
        );

        return new Catalog(serviceOfferings);
    }
}
