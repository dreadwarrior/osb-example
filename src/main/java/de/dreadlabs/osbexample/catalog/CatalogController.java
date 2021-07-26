package de.dreadlabs.osbexample.catalog;

import de.dreadlabs.osbexample.catalog.dto.Catalog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(
            CatalogService catalogService
    ) {
        this.catalogService = catalogService;
    }

    @GetMapping(headers = {"Accept=application/json", "X-Broker-API-Version=2.16"})
    public Catalog list() {
        return catalogService.list();
    }
}
