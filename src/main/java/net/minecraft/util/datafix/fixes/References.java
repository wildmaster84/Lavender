package net.minecraft.util.datafix.fixes;

import com.mojang.datafixers.DSL;

public class References {
    public static final DSL.TypeReference BLOCK_STATE = createRef();
    public static final DSL.TypeReference ITEM_STACK = createRef();
    public static final DSL.TypeReference BLOCK_ENTITY = createRef();
    public static final DSL.TypeReference CHUNK = createRef();
    public static final DSL.TypeReference ENTITY = createRef();
    public static final DSL.TypeReference PLAYER = createRef();
    public static final DSL.TypeReference LEVEL = createRef();
    public static final DSL.TypeReference SAVED_DATA = createRef();
    public static final DSL.TypeReference LEVEL_DATA = createRef();
    public static final DSL.TypeReference OPTIONS = createRef();
    public static final DSL.TypeReference STRUCTURE = createRef();

    private static DSL.TypeReference createRef() {
        return new DSL.TypeReference() {
            @Override
            public String typeName() {
                return "reference";
            }
        };
    }
}
