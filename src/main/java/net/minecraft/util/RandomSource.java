package net.minecraft.util;

public interface RandomSource {
    static RandomSource create() {
        return new RandomSourceImpl();
    }

    static RandomSource create(long seed) {
        return new RandomSourceImpl(seed);
    }

    int nextInt();

    int nextInt(int bound);

    long nextLong();

    boolean nextBoolean();

    float nextFloat();

    double nextDouble();

    double nextGaussian();
}
