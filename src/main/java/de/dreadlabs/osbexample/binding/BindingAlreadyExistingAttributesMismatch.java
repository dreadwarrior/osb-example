package de.dreadlabs.osbexample.binding;

public class BindingAlreadyExistingAttributesMismatch extends Exception {

    public BindingAlreadyExistingAttributesMismatch(String instanceId, String bindingId) {
        super("Binding '" + bindingId + "' of instance '" + instanceId + "' already created, but requested attributes does not match the existing ones.");
    }
}
