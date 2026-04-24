import java.util.*;

public class ItemDatabase {

    // MASTER ITEM MAP
    private static final Map<String, Item> items = new HashMap<>();

    // REGISTER helper
    private static Item reg(Item item) {
        items.put(item.id, item);
        return item;
    }

    // ========= POTIONS =========
    public static Item healthPotion() {
        return reg(new Item("hp_potion","Health Potion", Item.ItemType.CONSUMABLE,
                30, 15, true, 50, 0, 0, 0, 1));
    }

    public static Item greaterHealthPotion() {
        return reg(new Item("hp_greater","Greater Health Potion", Item.ItemType.CONSUMABLE,
                80, 40, true, 120, 0, 0, 0, 1));
    }

    public static Item energyPotion() {
        return reg(new Item("energy_potion","Energy Potion", Item.ItemType.CONSUMABLE,
                40, 20, true, 0, 40, 0, 0, 1));
    }

    public static Item manaPotion() {
        return reg(new Item("mana_potion","Mana Potion", Item.ItemType.CONSUMABLE,
                50, 25, true, 0, 60, 0, 0, 1));
    }


    // ========= WEAPONS =========
    public static Item rustySword() {
        return reg(new Item("rusty_sword","Rusty Sword", Item.ItemType.WEAPON,
                40, 20, false, 0, 0, 3, 0, 1));
    }

    public static Item ironSword() {
        return reg(new Item("iron_sword","Iron Sword", Item.ItemType.WEAPON,
                80, 40, false, 0, 0, 6, 0, 1));
    }

    public static Item steelSword() {
        return reg(new Item("steel_sword","Steel Sword", Item.ItemType.WEAPON,
                150, 75, false, 0, 0, 10, 0, 1));
    }

    public static Item noviceStaff() {
        return reg(new Item("novice_staff","Novice Staff", Item.ItemType.WEAPON,
                50, 25, false, 0, 20, 3, 0, 1));
    }

    public static Item oakStaff() {
        return reg(new Item("oak_staff","Oak Staff", Item.ItemType.WEAPON,
                120, 60, false, 0, 35, 6, 0, 1));
    }

    public static Item crystalStaff() {
        return reg(new Item("crystal_staff","Crystal Staff", Item.ItemType.WEAPON,
                260, 130, false, 0, 60, 10, 0, 1));
    }

    public static Item archmageScepter() {
        return reg(new Item("archmage_scepter","Archmage Scepter", Item.ItemType.WEAPON,
                600, 300, false, 0, 120, 15, 0, 1));
    }

    public static Item wornDagger() {
        return reg(new Item("worn_dagger","Worn Dagger", Item.ItemType.WEAPON,
                40, 20, false, 0, 0, 4, 0, 1));
    }

    public static Item shadowKnife() {
        return reg(new Item("shadow_knife","Shadow Knife", Item.ItemType.WEAPON,
                110, 55, false, 0, 0, 8, 0, 1));
    }

    public static Item nightStinger() {
        return reg(new Item("night_stinger","Night Stinger", Item.ItemType.WEAPON,
                240, 120, false, 0, 0, 14, 0, 1));
    }

    public static Item phantomEdge() {
        return reg(new Item("phantom_edge","Phantom Edge", Item.ItemType.WEAPON,
                550, 270, false, 0, 0, 22, 0, 1));
    }

    public static Item shortBow() {
        return reg(new Item("short_bow","Short Bow", Item.ItemType.WEAPON,
                70, 35, false, 0, 0, 5, 0, 1));
    }

    public static Item hunterBow() {
        return reg(new Item("hunter_bow","Hunter Bow", Item.ItemType.WEAPON,
                130, 65, false, 0, 0, 9, 0, 1));
    }

    public static Item longBow() {
        return reg(new Item("long_bow","Long Bow", Item.ItemType.WEAPON,
                260, 130, false, 0, 0, 14, 0, 1));
    }

    public static Item stormPiercer() {
        return reg(new Item("storm_piercer","Storm Piercer", Item.ItemType.WEAPON,
                580, 290, false, 0, 0, 22, 0, 1));
    }

    public static Item crackedTome() {
        return reg(new Item("cracked_tome","Cracked Tome", Item.ItemType.WEAPON,
                50, 25, false, 0, 15, 3, 0, 1));
    }

    public static Item boneGrimoire() {
        return reg(new Item("bone_grimoire","Bone Grimoire", Item.ItemType.WEAPON,
                110, 55, false, 0, 30, 6, 0, 1));
    }

    public static Item soulbinderTome() {
        return reg(new Item("soulbinder_tome","Soulbinder Tome", Item.ItemType.WEAPON,
                240, 120, false, 0, 50, 10, 0, 1));
    }

    public static Item deathWhisper() {
        return reg(new Item("death_whisper","Death Whisper", Item.ItemType.WEAPON,
                560, 280, false, 0, 100, 15, 0, 1));
    }

    public static Item rustlockPistol() {
        return reg(new Item("rustlock_pistol","Rustlock Pistol", Item.ItemType.WEAPON,
                90, 45, false, 0, 0, 7, 0, 1));
    }

    public static Item blackpowderRifle() {
        return reg(new Item("blackpowder_rifle","Blackpowder Rifle", Item.ItemType.WEAPON,
                300, 150, false, 0, 0, 18, 0, 1));
    }

    public static Item viperCarbine() {
        return reg(new Item("viper_carbine","Viper Carbine", Item.ItemType.WEAPON,
                520, 260, false, 0, 0, 26, 0, 1));
    }

    public static Item starflareHandcannon() {
        return reg(new Item("starflare_handcannon","Starflare Handcannon", Item.ItemType.WEAPON,
                800, 400, false, 0, 0, 35, 0, 1));
    }


    // ========= ARMOR =========
    public static Item travelersArmor() {
        return reg(new Item("traveler_armor","Traveler's Armor", Item.ItemType.ARMOR,
                70, 35, false, 0, 0, 0, 4, 1));
    }

    public static Item ironArmor() {
        return reg(new Item("iron_armor","Iron Armor", Item.ItemType.ARMOR,
                140, 70, false, 0, 0, 0, 8, 1));
    }

    public static Item steelArmor() {
        return reg(new Item("steel_armor","Steel Armor", Item.ItemType.ARMOR,
                250, 125, false, 0, 0, 0, 12, 1));
    }


    // ========= ACCESSORIES =========
    public static Item agilityRing() {
        return reg(new Item("agility_ring","Agility Ring", Item.ItemType.ACCESSORY,
                100, 50, false,
                0, 0, 3, 0,
                1, Item.AccessoryType.RING,
                20, 0));
    }

    public static Item stoneAmulet() {
        return reg(new Item("stone_amulet","Stone Amulet", Item.ItemType.ACCESSORY,
                140, 70, false,
                0, 0, 0, 5,
                1, Item.AccessoryType.PENDANT,
                0, 15));
    }

    public static Item wraithCharm() {
        return reg(new Item("wraith_charm","Wraith Charm", Item.ItemType.ACCESSORY,
                200, 100, false,
                0, 0, 0, 0,
                1, Item.AccessoryType.CHARM,
                10, 10));
    }


    // ===== BLACKSMITH ITEMS =====
    public static Item obsidianRing() {
        return reg(new Item("obsidian_ring","Obsidian Ring", Item.ItemType.ACCESSORY,
                200,30,false,
                0,0,5,0,
                1, Item.AccessoryType.RING,
                25,0));
    }

    public static Item guardianCharm() {
        return reg(new Item("guardian_charm","Guardian Charm", Item.ItemType.ACCESSORY,
                250,30,false,
                0,0,0,8,
                1, Item.AccessoryType.CHARM,
                40,10));
    }

    public static Item emberAmulet() {
        return reg(new Item("ember_amulet","Ember Amulet", Item.ItemType.ACCESSORY,
                300,40,false,
                0,20,0,0,
                1, Item.AccessoryType.PENDANT,
                0,30));
    }

    public static Item obsidianBlade() {
        return reg(new Item("obsidian_blade","Obsidian Blade", Item.ItemType.WEAPON,
                350,50,false,
                0,0,15,0,
                1));
    }

    public static Item phoenixMail() {
        return reg(new Item("phoenix_mail","Phoenix Mail", Item.ItemType.ARMOR,
                400,50,false,
                0,0,0,18,1));
    }

    public static Item ringOfFury() {
        return reg(new Item("ring_of_fury","Ring of Fury", Item.ItemType.ACCESSORY,
                280,40,false,
                0,0,8,0,
                1, Item.AccessoryType.RING,
                15,0));
    }

    public static Item soulbinderCharm() {
        return reg(new Item("soulbinder_charm","Soulbinder Charm", Item.ItemType.ACCESSORY,
                320,40,false,
                0,30,0,0,
                1, Item.AccessoryType.CHARM,
                10,30));
    }


    // ======== GET ITEM BY ID =========
    public static Item getItem(String id) {
        return items.get(id);
    }

    // ======== SHOP CREATION ========
    public static List<Item> createTownShop() {
        return List.of(
                healthPotion().copyWithQty(3),
                greaterHealthPotion().copyWithQty(1),
                energyPotion().copyWithQty(2),
                manaPotion().copyWithQty(2),
                rustySword().copyWithQty(1),
                ironSword().copyWithQty(1),
                steelSword().copyWithQty(1),
                travelersArmor().copyWithQty(1),
                ironArmor().copyWithQty(1),
                steelArmor().copyWithQty(1),
                agilityRing().copyWithQty(1),
                stoneAmulet().copyWithQty(1),
                wraithCharm().copyWithQty(1)
        );
    }
}
