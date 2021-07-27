package de.dreadlabs.osbexample.catalog.dto;

import java.util.Collection;

public record CatalogResponse(
        Collection<ServiceOffering> services
) { }