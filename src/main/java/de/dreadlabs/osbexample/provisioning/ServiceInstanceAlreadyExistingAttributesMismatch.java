package de.dreadlabs.osbexample.provisioning;

public class ServiceInstanceAlreadyExistingAttributesMismatch extends Exception {

    public ServiceInstanceAlreadyExistingAttributesMismatch(String instanceId) {
        super("Instance '" + instanceId + "' already provisioned, but requested attributes does not match the existing ones.");
    }
}
