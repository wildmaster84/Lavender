package net.minecraft.util.datafix;

import com.mojang.datafixers.DataFixer;

public class DataFixers {
    private static final DataFixer FIXER = new NoOpDataFixer();

    public static DataFixer getDataFixer() {
        return FIXER;
    }

    private static final class NoOpDataFixer implements DataFixer {
        @Override
        public <T> com.mojang.serialization.Dynamic<T> update(
                com.mojang.datafixers.DSL.TypeReference type,
                com.mojang.serialization.Dynamic<T> dynamic,
                int fromVersion,
                int toVersion) {
            return dynamic;
        }
    }
}
