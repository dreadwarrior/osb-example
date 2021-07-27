package de.dreadlabs.osbexample.common;

public record ServiceBrokerError(
        String error,
        String description
) { }
