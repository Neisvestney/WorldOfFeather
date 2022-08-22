package net.neisvestney.yarabirds.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.neisvestney.yarabirds.YaraBirds;

public class YaraBirdsItems {
    public static final Item RAVEN_SPAWN_EGG = registerItem("raven_spawn_egg", new SpawnEggItem(YaraBirdsEntities.RAVEN, 0, 2894892, new Item.Settings().group(ItemGroup.MISC)));

    private static Item registerItem(String identifier, Item item) {
        Registry.register(Registry.ITEM, new Identifier(YaraBirds.MOD_ID, identifier), item);
        return item;
    }

    public static void register() {}
}
