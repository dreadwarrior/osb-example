package de.dreadlabs.osbexample.provisioning.dto;

import java.util.HashMap;

public record ServiceInstanceMetadata(
        HashMap<String, String> labels
) { }
