package de.dreadlabs.osbexample.provisioning;

public record ServiceBrokerError(
        String error,
        String description
) { }
