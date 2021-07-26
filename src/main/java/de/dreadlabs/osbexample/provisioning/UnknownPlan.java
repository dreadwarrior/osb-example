package de.dreadlabs.osbexample.provisioning;

public class UnknownPlan extends Exception {
    public UnknownPlan(String planId) {
        super("Unknown plan '" + planId + "' requested for provisioning.");
    }
}
