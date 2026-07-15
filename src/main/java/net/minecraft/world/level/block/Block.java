package net.minecraft.world.level.block;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Block {
    private static final Map<String, Block> CACHE = new ConcurrentHashMap<>();

    public static final Block AIR = of(net.minestom.server.instance.block.Block.AIR);

    private final net.minestom.server.instance.block.Block minestomBlock;
    private StateDefinition<Block, BlockState> stateDefinition;

    public Block() {
        this(net.minestom.server.instance.block.Block.AIR);
    }

    public Block(net.minestom.server.instance.block.Block minestomBlock) {
        this.minestomBlock = minestomBlock;
    }

    public static Block of(net.minestom.server.instance.block.Block msBlock) {
        net.minestom.server.instance.block.Block block = msBlock != null ? msBlock : net.minestom.server.instance.block.Block.AIR;
        return CACHE.computeIfAbsent(block.name(), k -> new Block(block));
    }

    public net.minestom.server.instance.block.Block getMinestomBlock() {
        return minestomBlock;
    }

    public static int getId(BlockState state) {
        return state.getId();
    }

    public static BlockState stateById(int id) {
        net.minestom.server.instance.block.Block msBlock = net.minestom.server.instance.block.Block.fromStateId(id);
        if (msBlock == null) msBlock = net.minestom.server.instance.block.Block.AIR;
        return new BlockState(of(msBlock.defaultState()), msBlock);
    }

    public static boolean isShapeFullBlock(VoxelShape shape) {
        return shape == VoxelShape.FULL;
    }

    public static BlockState updateFromNeighbourShapes(BlockState state, net.minecraft.world.level.LevelAccessor accessor, net.minecraft.core.BlockPos pos) {
        return state;
    }

    public BlockState defaultBlockState() {
        return new BlockState(this, minestomBlock.defaultState());
    }

    public String getDescriptionId() {
        return "block." + minestomBlock.name();
    }

    public float getExplosionResistance() {
        return minestomBlock.registry().explosionResistance();
    }

    public float getFriction() {
        return minestomBlock.registry().friction();
    }

    public StateDefinition<Block, BlockState> getStateDefinition() {
        if (stateDefinition != null) return stateDefinition;

        Map<String, Set<String>> propValues = new LinkedHashMap<>();
        for (net.minestom.server.instance.block.Block state : minestomBlock.possibleStates()) {
            for (Map.Entry<String, String> entry : state.properties().entrySet()) {
                propValues.computeIfAbsent(entry.getKey(), k -> new LinkedHashSet<>()).add(entry.getValue());
            }
        }

        List<Property<?>> properties = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : propValues.entrySet()) {
            properties.add(createProperty(entry.getKey(), entry.getValue()));
        }

        List<BlockState> states = new ArrayList<>();
        for (net.minestom.server.instance.block.Block state : minestomBlock.possibleStates()) {
            states.add(new BlockState(this, state));
        }

        stateDefinition = new StateDefinition<>(this, defaultBlockState(), properties, states);
        return stateDefinition;
    }

    private static Property<?> createProperty(String name, Set<String> values) {
        boolean allBoolean = true;
        boolean allInteger = true;
        for (String v : values) {
            if (!v.equals("true") && !v.equals("false")) allBoolean = false;
            try {
                Integer.parseInt(v);
            } catch (NumberFormatException e) {
                allInteger = false;
            }
        }
        if (allBoolean) {
            return new BooleanProperty(name);
        } else if (allInteger) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (String v : values) {
                int i = Integer.parseInt(v);
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
            return new IntegerProperty(name, min, max);
        } else {
            return new EnumProperty<>(name, values);
        }
    }

    public boolean hasCollision() {
        return true;
    }

    public boolean isPossibleToRespawnInThis() {
        return minestomBlock.registry().isSolid();
    }

    public net.minecraft.world.item.Item asItem() {
        net.minestom.server.item.Material mat = minestomBlock.registry().material();
        if (mat == null) return null;
        return new net.minecraft.world.item.Item(mat);
    }
}
