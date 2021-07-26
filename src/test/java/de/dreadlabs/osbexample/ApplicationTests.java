package de.dreadlabs.osbexample;

import de.dreadlabs.osbexample.catalog.CatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private CatalogService catalogService;

    @Test
    void contextLoads() {
        assertThat(catalogService).isNotNull();
    }

}
