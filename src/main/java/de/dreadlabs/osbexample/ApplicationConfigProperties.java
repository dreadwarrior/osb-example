package de.dreadlabs.osbexample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "osb-example")
@ConstructorBinding
public record ApplicationConfigProperties(
        String userName,
        String password
) { }
