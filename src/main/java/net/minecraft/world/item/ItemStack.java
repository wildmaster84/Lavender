package net.minecraft.world.item;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemStack {
    public static final ItemStack EMPTY = new ItemStack();

    private final net.minestom.server.item.ItemStack minestomStack;

    public ItemStack() {
        this.minestomStack = net.minestom.server.item.ItemStack.AIR;
    }

    public ItemStack(net.minestom.server.item.ItemStack minestomStack) {
        this.minestomStack = minestomStack;
    }

    public ItemStack(Holder<Item> holder, int count) {
        Item item = holder.value();
        net.minestom.server.item.Material mat = item.getMaterial();
        this.minestomStack = net.minestom.server.item.ItemStack.of(mat, count);
    }

    public net.minestom.server.item.ItemStack getMinestomStack() {
        return minestomStack;
    }

    public Item getItem() {
        return new Item(minestomStack.material());
    }

    public Component getItemName() {
        net.kyori.adventure.text.Component displayName = minestomStack.get(net.minestom.server.component.DataComponents.CUSTOM_NAME);
        if (displayName == null) displayName = minestomStack.get(net.minestom.server.component.DataComponents.ITEM_NAME);
        if (displayName == null) displayName = net.kyori.adventure.text.Component.text(minestomStack.material().name());
        return new net.minecraft.network.chat.MutableComponent(displayName);
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    public boolean isEmpty() {
        return minestomStack.isAir();
    }

    public DataComponentPatch getComponentsPatch() {
        return DataComponentPatch.EMPTY;
    }

    public void applyComponents(DataComponentPatch patch) {
    }

    public InteractionResult useOn(net.minecraft.world.item.context.UseOnContext context) {
        return InteractionResult.PASS;
    }
}
