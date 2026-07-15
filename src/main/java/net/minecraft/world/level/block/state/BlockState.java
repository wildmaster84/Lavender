package net.minecraft.world.level.block.state;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockState {
    private final Block block;
    private final net.minestom.server.instance.block.Block minestomBlock;
    private Map<Property<?>, Comparable<?>> properties;

    public BlockState() {
        this.block = Block.of(net.minestom.server.instance.block.Block.AIR);
        this.minestomBlock = net.minestom.server.instance.block.Block.AIR;
    }

    public BlockState(Block block, net.minestom.server.instance.block.Block minestomBlock) {
        this.block = block;
        this.minestomBlock = minestomBlock;
    }

    public Block getBlock() {
        return block;
    }

    public int getId() {
        return minestomBlock.stateId();
    }

    public net.minestom.server.instance.block.Block getMinestomBlock() {
        return minestomBlock;
    }

    public boolean isAir() {
        return minestomBlock.isAir();
    }

    public boolean isSolid() {
        return !isAir();
    }

    public boolean liquid() {
        return minestomBlock.isLiquid();
    }

    public boolean canBeReplaced() {
        return false;
    }

    public boolean canOcclude() {
        return false;
    }

    public boolean blocksMotion() {
        return !isAir();
    }

    public boolean hasBlockEntity() {
        return minestomBlock.registry().isBlockEntity();
    }

    public boolean isRandomlyTicking() {
        return false;
    }

    public boolean isSignalSource() {
        return false;
    }

    public boolean hasAnalogOutputSignal() {
        return false;
    }

    public boolean requiresCorrectToolForDrops() {
        return false;
    }

    public boolean ignitedByLava() {
        return false;
    }

    public int getLightEmission() {
        return 0;
    }

    public float getDestroySpeed(net.minecraft.world.level.BlockGetter getter, BlockPos pos) {
        return 0;
    }

    public PushReaction getPistonPushReaction() {
        return PushReaction.BLOCK;
    }

    public VoxelShape getShape(net.minecraft.world.level.BlockGetter getter, BlockPos pos) {
        return VoxelShape.EMPTY;
    }

    public VoxelShape getCollisionShape(net.minecraft.world.level.BlockGetter getter, BlockPos pos) {
        return VoxelShape.EMPTY;
    }

    public VoxelShape getOcclusionShape(net.minecraft.world.level.BlockGetter getter, BlockPos pos) {
        return VoxelShape.EMPTY;
    }

    public boolean canSurvive(net.minecraft.world.level.LevelReader reader, BlockPos pos) {
        return true;
    }

    public void onPlace(net.minecraft.world.level.Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
    }

    public void handleNeighborChanged(net.minecraft.world.level.Level level, BlockPos pos, Block block, net.minecraft.world.level.redstone.Orientation orientation, boolean isMoving) {
    }

    public void updateNeighbourShapes(net.minecraft.world.level.LevelAccessor accessor, BlockPos pos, int flags, int recursionLimit) {
    }

    public void updateIndirectNeighbourShapes(net.minecraft.world.level.LevelAccessor accessor, BlockPos pos, int flags, int recursionLimit) {
    }

    public BlockState setValue(Property property, Comparable value) {
        String propName = property.getName();
        String propValue = valueToString(value);
        net.minestom.server.instance.block.Block newMsBlock = minestomBlock.withProperty(propName, propValue);
        return new BlockState(block, newMsBlock);
    }

    public Comparable getValue(Property property) {
        String value = minestomBlock.getProperty(property.getName());
        if (value == null) return null;
        return stringToValue(property, value);
    }

    public boolean hasProperty(Property<?> property) {
        return minestomBlock.properties().containsKey(property.getName());
    }

    public StateDefinition<Block, BlockState> getOwner() {
        return block.getStateDefinition();
    }

    public Map<Property<?>, Comparable<?>> getValues() {
        if (properties != null) return properties;
        properties = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : minestomBlock.properties().entrySet()) {
            Property<?> prop = block.getStateDefinition().getProperty(entry.getKey());
            if (prop != null) {
                Comparable<?> val = stringToValue(prop, entry.getValue());
                if (val != null) {
                    properties.put(prop, val);
                }
            }
        }
        return properties;
    }

    public Object getMaterial() {
        return null;
    }

    public int getAnalogOutputSignal(net.minecraft.world.level.Level level, BlockPos pos) {
        return 0;
    }

    public boolean isFaceSturdy(net.minecraft.world.level.BlockGetter getter, BlockPos pos, Direction direction) {
        return false;
    }

    public boolean isFaceSturdy(net.minecraft.world.level.BlockGetter getter, BlockPos pos, Direction direction, net.minecraft.world.level.block.state.properties.BlockStateProperties.FaceSearchPosition position) {
        return false;
    }

    public net.minecraft.world.item.ItemStack getItem(net.minecraft.world.level.LevelReader reader, BlockPos pos) {
        return new net.minecraft.world.item.ItemStack();
    }

    public void onRemove(net.minecraft.world.level.Level level, BlockPos pos, BlockState newState, boolean isMoving) {
    }

    public void neighborChanged(net.minecraft.world.level.Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
    }

    public BlockState mirror(net.minecraft.world.level.block.Mirror mirror) {
        return this;
    }

    public BlockState rotate(net.minecraft.world.level.block.Rotation rotation) {
        return this;
    }

    public int getLightBlock(net.minecraft.world.level.BlockGetter getter, BlockPos pos) {
        return 0;
    }

    public int getSignal(net.minecraft.world.level.BlockGetter getter, BlockPos pos, Direction direction) {
        return 0;
    }

    public int getDirectSignal(net.minecraft.world.level.BlockGetter getter, BlockPos pos, Direction direction) {
        return 0;
    }

    public boolean use(net.minecraft.world.level.Level level, net.minecraft.world.entity.player.Player player, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
        return false;
    }

    public net.minecraft.world.InteractionResult useItemOn(net.minecraft.world.item.ItemStack stack, net.minecraft.world.level.Level level, net.minecraft.world.entity.player.Player player, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
        return net.minecraft.world.InteractionResult.PASS;
    }

    public String toString() {
        return minestomBlock.toString();
    }

    private static String valueToString(Comparable value) {
        if (value instanceof Direction) {
            return ((Direction) value).name().toLowerCase();
        } else if (value instanceof StringRepresentable) {
            return ((StringRepresentable) value).getSerializedName();
        }
        return value.toString();
    }

    @SuppressWarnings("unchecked")
    private static Comparable stringToValue(Property<?> property, String value) {
        if (property instanceof BooleanProperty) {
            return Boolean.parseBoolean(value);
        } else if (property instanceof IntegerProperty) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (property instanceof EnumProperty) {
            @SuppressWarnings("rawtypes")
            Optional opt = ((EnumProperty) property).getValue(value);
            return (Comparable) opt.orElse(null);
        }
        return value;
    }
}
