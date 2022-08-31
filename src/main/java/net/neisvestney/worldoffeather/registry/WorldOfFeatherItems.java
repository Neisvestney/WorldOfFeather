package net.neisvestney.worldoffeather.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.neisvestney.worldoffeather.WorldOfFeather;

public class WorldOfFeatherItems {
    public static final Item MAGPIE_FEATHER = registerItem("magpie_feather", new Item(new Item.Settings().group(ItemGroup.MISC)));
    public static final Item RAVEN_FEATHER = registerItem("raven_feather", new Item(new Item.Settings().group(ItemGroup.MISC)));

    public static final Item HOODED_CROW_SPAWN_EGG = registerItem("hooded_crow_spawn_egg", new SpawnEggItem(WorldOfFeatherEntities.HOODED_CROW, 12432301, 3354671, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item MAGPIE_SPAWN_EGG = registerItem("magpie_spawn_egg", new SpawnEggItem(WorldOfFeatherEntities.MAGPIE, 16777215, 2504278, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item RAVEN_SPAWN_EGG = registerItem("raven_spawn_egg", new SpawnEggItem(WorldOfFeatherEntities.RAVEN, 2302755, 1907997, new Item.Settings().group(ItemGroup.MISC)));

    private static Item registerItem(String identifier, Item item) {
        Registry.register(Registry.ITEM, new Identifier(WorldOfFeather.MOD_ID, identifier), item);
        return item;
    }

    public static void register() {
    }
}
