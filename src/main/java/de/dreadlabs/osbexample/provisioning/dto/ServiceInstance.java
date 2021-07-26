package de.dreadlabs.osbexample.provisioning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServiceInstance (
        @JsonProperty("dashboard_url") String dashboardUrl
) { }
