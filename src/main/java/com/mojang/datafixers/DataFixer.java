package com.mojang.datafixers;

import com.mojang.serialization.Dynamic;

public interface DataFixer {
    <T> Dynamic<T> update(DSL.TypeReference type, Dynamic<T> dynamic, int fromVersion, int toVersion);
}
