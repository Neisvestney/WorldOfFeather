package net.neisvestney.yarabirds.mixin;


import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.neisvestney.yarabirds.registry.YaraBirdsItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.Set;

@Mixin(ItemGroup.class)
public class ItemGroupMixin {

    // Mixin to reorder custom spawn eggs in minecraft mics tab
    @Inject(at = @At("RETURN"), method = "appendStacks")
    public void appendStacks(DefaultedList<ItemStack> stacks, CallbackInfo ci) {

        int i = 0;
        for (Item item : Set.of(YaraBirdsItems.HOODED_CROW_SPAWN_EGG, YaraBirdsItems.MAGPIE_SPAWN_EGG, YaraBirdsItems.RAVEN_SPAWN_EGG)) {
            Optional<ItemStack> optionalItemStack = stacks.stream().filter(stack -> stack.getItem() == item.asItem()).findFirst();
            if (optionalItemStack.isPresent()) {
                stacks.remove(optionalItemStack.get());
                stacks.add(147 + i, optionalItemStack.get()); // Right after last spawn egg
                i++;
            }
        }
    }
}
