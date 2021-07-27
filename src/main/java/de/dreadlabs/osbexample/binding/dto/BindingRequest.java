package de.dreadlabs.osbexample.binding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record BindingRequest(
        @JsonProperty("service_id") @NotBlank @NotNull @NotEmpty String serviceId,
        @JsonProperty("plan_id") @NotBlank @NotNull @NotEmpty String planId,
        @JsonProperty("app_guid") String appGuid,
        @JsonProperty("bind_resource") BindResource bindResource
) {

    private record BindResource(
            @JsonProperty("app_guid") String appGuid,
            @JsonProperty String route
    ) {
    }
}
