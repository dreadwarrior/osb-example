package de.dreadlabs.osbexample;

import de.dreadlabs.osbexample.binding.BindingService;
import de.dreadlabs.osbexample.catalog.CatalogService;
import de.dreadlabs.osbexample.provisioning.ProvisioningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private ProvisioningService provisioningService;

    @Autowired
    private BindingService bindingService;

    @Test
    void contextLoads() {
        assertThat(catalogService).isNotNull();
        assertThat(provisioningService).isNotNull();
        assertThat(bindingService).isNotNull();
    }

}
