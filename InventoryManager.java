import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryManager {

    // Add item to player's inventory (handles stacking)
    public static void addItem(Player player, Item itemToAdd) {
        if (itemToAdd == null) return;

        if (itemToAdd.stackable) {
            Optional<Item> existing = player.getInventory().stream()
                    .filter(i -> i.id.equals(itemToAdd.id))
                    .findFirst();
            if (existing.isPresent()) {
                existing.get().qty += itemToAdd.qty;
            } else {
                player.getInventory().add(itemToAdd.copyWithQty(itemToAdd.qty));
            }
        } else {
            for (int i = 0; i < Math.max(1, itemToAdd.qty); i++) {
                player.getInventory().add(itemToAdd.copyWithQty(1));
            }
        }
    }

    // Remove quantity (returns true if removed)
    public static boolean removeItem(Player player, String itemId, int qty) {
        List<Item> inv = player.getInventory();
        for (int i = 0; i < inv.size(); i++) {
            Item it = inv.get(i);

            if (it.id.equals(itemId)) {
                if (it.stackable) {
                    if (it.qty >= qty) {
                        it.qty -= qty;
                        if (it.qty == 0) inv.remove(i);
                        return true;
                    }
                    return false;
                }

                // Non-stackable
                int removed = 0;
                int j = i;
                while (j < inv.size() && removed < qty) {
                    if (inv.get(j).id.equals(itemId)) {
                        inv.remove(j);
                        removed++;
                    } else j++;
                }
                return removed > 0;
            }
        }
        return false;
    }

    // Find item by id (first occurrence)
    public static Item findItem(Player player, String itemId) {
        return player.getInventory().stream()
                .filter(i -> i.id.equals(itemId))
                .findFirst().orElse(null);
    }

    // Use a consumable
    public static boolean useConsumable(Player player, Item item) {
        if (item == null) return false;
        if (item.type != Item.ItemType.CONSUMABLE) return false;

        if (item.hpRestore > 0)
            player.setHP(Math.min(player.getHP() + item.hpRestore, getPlayerMaxHP(player)));

        if (item.energyRestore > 0)
            player.restoreEnergy(item.energyRestore);

        removeItem(player, item.id, 1);
        return true;
    }


    // ---------------------------------------------
    // 3 ACCESSORY SLOTS: RING / PENDANT / CHARM
    // ---------------------------------------------
    public static boolean equipItem(Player player, Item item) {
        if (item == null) return false;

        switch (item.type) {

            case WEAPON -> {
                if (player.equipWeapon != null)
                    player.getInventory().add(player.equipWeapon);

                player.equipWeapon = item;
                removeItem(player, item.id, 1);
                return true;
            }

            case ARMOR -> {
                if (player.equipArmor != null)
                    player.getInventory().add(player.equipArmor);

                player.equipArmor = item;
                removeItem(player, item.id, 1);
                return true;
            }

            case ACCESSORY -> {
                return equipAccessory(player, item);
            }

            default -> {
                return false;
            }
        }
    }


    // Equip Accessory into correct slot
    private static boolean equipAccessory(Player p, Item item) {

        switch (item.accessoryType) {

            case RING -> {
                if (p.ringSlot != null)
                    p.getInventory().add(p.ringSlot);

                p.ringSlot = item;
                removeItem(p, item.id, 1);
                return true;
            }

            case PENDANT -> {
                if (p.pendantSlot != null)
                    p.getInventory().add(p.pendantSlot);

                p.pendantSlot = item;
                removeItem(p, item.id, 1);
                return true;
            }

            case CHARM -> {
                if (p.charmSlot != null)
                    p.getInventory().add(p.charmSlot);

                p.charmSlot = item;
                removeItem(p, item.id, 1);
                return true;
            }

            default -> {
                return false;
            }
        }
    }


    // ---------------------------------------------
    // UNEQUIP SYSTEM (weapon, armor, ring, pendant, charm)
    // ---------------------------------------------
    public static boolean unequip(Player p, String slot) {
        slot = slot.toLowerCase();

        switch (slot) {

            case "weapon" -> {
                if (p.equipWeapon == null) return false;
                p.getInventory().add(p.equipWeapon);
                p.equipWeapon = null;
                return true;
            }

            case "armor" -> {
                if (p.equipArmor == null) return false;
                p.getInventory().add(p.equipArmor);
                p.equipArmor = null;
                return true;
            }

            case "ring" -> {
                if (p.ringSlot == null) return false;
                p.getInventory().add(p.ringSlot);
                p.ringSlot = null;
                return true;
            }

            case "pendant" -> {
                if (p.pendantSlot == null) return false;
                p.getInventory().add(p.pendantSlot);
                p.pendantSlot = null;
                return true;
            }

            case "charm" -> {
                if (p.charmSlot == null) return false;
                p.getInventory().add(p.charmSlot);
                p.charmSlot = null;
                return true;
            }
        }

        return false;
    }


    // Pretty print inventory (returns string lines)
    public static List<String> listInventory(Player player) {
        List<String> lines = new ArrayList<>();
        List<Item> inv = player.getInventory();

        if (inv.isEmpty()) {
            lines.add("Inventory is empty.");
            return lines;
        }

        int idx = 1;
        for (Item it : inv) {
            lines.add("[" + (idx++) + "] " + it.toString());
        }
        return lines;
    }


    // Helper for private maxHp
    private static int getPlayerMaxHP(Player player) {
        try {
            var f = Player.class.getDeclaredField("maxHp");
            f.setAccessible(true);
            return (int) f.get(player);
        } catch (Exception ex) {
            return 100;
        }
    }
}
