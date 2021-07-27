package de.dreadlabs.osbexample.common;

public class ExistingEntityAttributesMismatch extends Exception {

    private ExistingEntityAttributesMismatch(String message) {
        super(message);
    }

    public static ExistingEntityAttributesMismatch provisioning(String instanceId) {
        return new ExistingEntityAttributesMismatch("Instance '" + instanceId + "' already provisioned, but requested attributes does not match the existing ones.");
    }

    public static ExistingEntityAttributesMismatch binding(String instanceId, String bindingId) {
        return new ExistingEntityAttributesMismatch("Binding '" + bindingId + "' of instance '" + instanceId + "' already created, but requested attributes does not match the existing ones.");
    }
}
