package de.dreadlabs.osbexample.common.dto;

public record ServiceBrokerErrorResponse(
        String error,
        String description
) { }
