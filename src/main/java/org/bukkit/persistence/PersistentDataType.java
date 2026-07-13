package org.bukkit.persistence;

public class PersistentDataType<T> {
    private final Class<T> complexType;
    private final Class<?> primitiveType;

    protected PersistentDataType(Class<T> complexType, Class<?> primitiveType) {
        this.complexType = complexType;
        this.primitiveType = primitiveType;
    }

    public Class<T> getComplexType() { return complexType; }
    public Class<?> getPrimitiveType() { return primitiveType; }

    public static final PersistentDataType<Byte> BYTE = new PersistentDataType<>(Byte.class, byte.class);
    public static final PersistentDataType<Short> SHORT = new PersistentDataType<>(Short.class, short.class);
    public static final PersistentDataType<Integer> INTEGER = new PersistentDataType<>(Integer.class, int.class);
    public static final PersistentDataType<Long> LONG = new PersistentDataType<>(Long.class, long.class);
    public static final PersistentDataType<Float> FLOAT = new PersistentDataType<>(Float.class, float.class);
    public static final PersistentDataType<Double> DOUBLE = new PersistentDataType<>(Double.class, double.class);
    public static final PersistentDataType<String> STRING = new PersistentDataType<>(String.class, String.class);
    public static final PersistentDataType<byte[]> BYTE_ARRAY = new PersistentDataType<>(byte[].class, byte[].class);
}
