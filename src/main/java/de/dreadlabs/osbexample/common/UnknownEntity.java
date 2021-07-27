package de.dreadlabs.osbexample.common;

public class UnknownEntity extends Exception {

    private UnknownEntity(String message) {
        super(message);
    }

    public static UnknownEntity service(String serviceId) {
        return new UnknownEntity("Unknown service '" + serviceId + "' requested for provisioning.");
    }

    public static UnknownEntity plan(String planId) {
        return new UnknownEntity("Unknown plan '" + planId + "' requested for provisioning.");
    }
}
