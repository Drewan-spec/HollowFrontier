public class Item {
    public enum ItemType { CONSUMABLE, WEAPON, ARMOR, ACCESSORY }
    public enum AccessoryType { RING, PENDANT, CHARM }

    public final String id;
    public final String name;
    public final ItemType type;
    public final int buyPrice;
    public final int sellPrice;
    public final boolean stackable;

    public int qty;

    public final int hpRestore;
    public final int energyRestore;
    public final int atkBonus;
    public final int defBonus;
    public final int hpBonus;      // Extra max HP if accessory
    public final int energyBonus;  // Extra max Energy if accessory

    // NEW
    public final AccessoryType accessoryType;

    // MAIN constructor (used for everything)
    public Item(String id, String name, ItemType type,
                int buyPrice, int sellPrice, boolean stackable,
                int hpRestore, int energyRestore, int atkBonus, int defBonus,
                int qty, AccessoryType accessoryType,
                int hpBonus, int energyBonus) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stackable = stackable;

        this.hpRestore = hpRestore;
        this.energyRestore = energyRestore;
        this.atkBonus = atkBonus;
        this.defBonus = defBonus;

        this.qty = Math.max(1, qty);

        this.accessoryType = accessoryType;

        this.hpBonus = hpBonus;
        this.energyBonus = energyBonus;
    }

    // ✅ BACKWARDS-COMPATIBLE constructor (no accessory type)
    // This makes your old calls STILL WORK:
    public Item(String id, String name, ItemType type,
                int buyPrice, int sellPrice, boolean stackable,
                int hpRestore, int energyRestore, int atkBonus, int defBonus,
                int qty) {
        this(id, name, type, buyPrice, sellPrice, stackable,
                hpRestore, energyRestore, atkBonus, defBonus,
                qty, null, 0, 0); // non-accessories default to null
    }

    public Item copyWithQty(int newQty) {
        return new Item(id, name, type, buyPrice, sellPrice, stackable,
                hpRestore, energyRestore, atkBonus, defBonus,
                newQty, accessoryType,
                hpBonus, energyBonus);
    }


    @Override
    public String toString() {
        if (stackable) return name + " x" + qty;
        return name;
    }
}
