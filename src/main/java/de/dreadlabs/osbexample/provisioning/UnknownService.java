package de.dreadlabs.osbexample.provisioning;

public class UnknownService extends Exception {

    public UnknownService(String serviceId) {
        super("Unknown service '" + serviceId + "' requested for provisioning.");
    }
}
