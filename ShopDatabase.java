import java.util.HashMap;

public class ShopDatabase {

    // ===== SHOP IDs =====
    public static final int ASH_HOLLOW_SHOP = 2;
    public static final int GHOST_TOWN_SHOP = 13;
    public static final int CANYON_SHOP = 18;

    // Stores all shops
    private static final HashMap<Integer, Item[]> shops = new HashMap<>();

    static {

        // =====================================================
        // ASH HOLLOW SHOP — starter gear
        // =====================================================
        shops.put(ASH_HOLLOW_SHOP, new Item[] {
                ItemDatabase.healthPotion().copyWithQty(1),
                ItemDatabase.manaPotion().copyWithQty(1),
                null,  // CLASS WEAPON SLOT
                ItemDatabase.travelersArmor().copyWithQty(1),
                ItemDatabase.agilityRing().copyWithQty(1)
        });



        // =====================================================
        // GHOST TOWN SHOP — mid–game gear
        // =====================================================
        shops.put(GHOST_TOWN_SHOP, new Item[] {
                ItemDatabase.greaterHealthPotion().copyWithQty(1),
                ItemDatabase.manaPotion().copyWithQty(1),
                null,  // CLASS WEAPON SLOT
                ItemDatabase.ironArmor().copyWithQty(1),
                ItemDatabase.stoneAmulet().copyWithQty(1)
        });



        // =====================================================
        // CANYON SHOP — late–game gear
        // =====================================================
        shops.put(CANYON_SHOP, new Item[] {
                ItemDatabase.healthPotion().copyWithQty(2),
                ItemDatabase.manaPotion().copyWithQty(2),
                null,  // CLASS WEAPON SLOT
                ItemDatabase.steelArmor().copyWithQty(1),
                ItemDatabase.wraithCharm().copyWithQty(1)
        });



    }

    // ===== CLASS-BASED WEAPON POOLS =====
    public static Item[] swordsmanWeapons = {
            ItemDatabase.rustySword(),
            ItemDatabase.ironSword(),
            ItemDatabase.steelSword()
    };

    public static Item[] mageWeapons = {
            ItemDatabase.noviceStaff(),
            ItemDatabase.oakStaff(),
            ItemDatabase.crystalStaff()
    };

    public static Item[] assassinWeapons = {
            ItemDatabase.wornDagger(),
            ItemDatabase.shadowKnife(),
            ItemDatabase.nightStinger()
    };

    public static Item[] rangerWeapons = {
            ItemDatabase.shortBow(),
            ItemDatabase.hunterBow(),
            ItemDatabase.longBow(),
            ItemDatabase.rustlockPistol(),
    };

    public static Item[] necromancerWeapons = {
            ItemDatabase.crackedTome(),
            ItemDatabase.boneGrimoire(),
            ItemDatabase.soulbinderTome()
    };


    // PUBLIC ACCESSOR
    public static Item[] getShop(int id, Player player) {

        Item[] base = shops.get(id);
        if (base == null) return null;

        Item[] result = base.clone();

        // Insert correct weapon
        if (result.length > 2 && result[2] == null) {
            if (player instanceof Swordsman)
                result[2] = getTieredWeapon(swordsmanWeapons, id);
            else if (player instanceof Mage)
                result[2] = getTieredWeapon(mageWeapons, id);
            else if (player instanceof Assassin)
                result[2] = getTieredWeapon(assassinWeapons, id);
            else if (player instanceof Ranger)
                result[2] = getTieredWeapon(rangerWeapons, id);
            else if (player instanceof Necromancer)
                result[2] = getTieredWeapon(necromancerWeapons, id);
        }

        return result;
    }

    private static Item getTieredWeapon(Item[] list, int shopId) {
        switch (shopId) {
            case ASH_HOLLOW_SHOP -> {
                return list[0].copyWithQty(1);
            }
            case GHOST_TOWN_SHOP -> {
                return list.length > 1 ? list[1].copyWithQty(1) : list[0].copyWithQty(1);
            }
            case CANYON_SHOP -> {
                return list.length > 2 ? list[2].copyWithQty(1) : list[list.length - 1].copyWithQty(1);
            }
            default -> {
                return list[0].copyWithQty(1);
            }
        }
    }

}
