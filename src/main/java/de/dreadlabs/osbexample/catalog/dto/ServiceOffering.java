package de.dreadlabs.osbexample.catalog.dto;

import java.util.Collection;

public record ServiceOffering(
        String id,
        String name,
        String description,
        Boolean bindable,
        Collection<ServicePlan> plans
) { }
