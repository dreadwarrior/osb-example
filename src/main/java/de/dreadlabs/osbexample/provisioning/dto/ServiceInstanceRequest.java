package de.dreadlabs.osbexample.provisioning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public record ServiceInstanceRequest(
    @JsonProperty("service_id") String serviceId,
    @JsonProperty("plan_id") String planId,
    HashMap<String, String> context,
    @JsonProperty("organization_guid") String organizationGUID,
    @JsonProperty("space_guid") String spaceGUID
) { }
