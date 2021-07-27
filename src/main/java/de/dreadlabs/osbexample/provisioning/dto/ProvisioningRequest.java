package de.dreadlabs.osbexample.provisioning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

public record ProvisioningRequest(
    @JsonProperty("service_id") @NotBlank @NotNull @NotEmpty String serviceId,
    @JsonProperty("plan_id") @NotBlank @NotNull @NotEmpty String planId,
    HashMap<String, String> context,
    @JsonProperty("organization_guid") @NotBlank @NotNull @NotEmpty String organizationGUID,
    @JsonProperty("space_guid") @NotBlank @NotNull @NotEmpty String spaceGUID
) { }
