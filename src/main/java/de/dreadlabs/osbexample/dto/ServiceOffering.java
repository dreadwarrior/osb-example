package de.dreadlabs.osbexample.dto;

import java.util.Collection;

public record ServiceOffering(
        String name,
        String id,
        String description,
        Boolean bindable,
        Collection<ServicePlan> plans
) { }
