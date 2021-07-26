package de.dreadlabs.osbexample.catalog.dto;

import java.util.Collection;

public record Catalog(
        Collection<ServiceOffering> services
) {}