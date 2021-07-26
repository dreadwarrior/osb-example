package de.dreadlabs.osbexample.provisioning.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ServiceInstance (
        String dashboardUrl,
        String operation,
        ServiceInstanceMetadata metadata
) { }
