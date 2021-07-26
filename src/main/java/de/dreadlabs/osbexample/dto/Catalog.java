package de.dreadlabs.osbexample.dto;

import java.util.Collection;

public record Catalog(
        Collection<ServiceOffering> services
) {}