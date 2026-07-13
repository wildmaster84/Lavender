package org.bukkit.attribute;

public class AttributeModifier {
    public AttributeModifier(String name, double amount, Operation operation) {}
    public AttributeModifier(org.bukkit.NamespacedKey key, double amount, Operation operation) {}
    public String getName() { return null; }
    public double getAmount() { return 0; }
    public Operation getOperation() { return null; }
    public enum Operation { ADD_NUMBER, ADD_SCALAR, MULTIPLY_SCALAR_1 }
}
