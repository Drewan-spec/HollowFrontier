import java.util.*;

public class InventoryUI {

    private static final Scanner sc = new Scanner(System.in);

    public static void open(Player player) {

        while (true) {

            System.out.println("\n====== INVENTORY ======");
            System.out.println("Coins: " + player.getCoins());

            // Categorized item lists
            List<Item> consumables = new ArrayList<>();
            List<Item> weapons = new ArrayList<>();
            List<Item> armor = new ArrayList<>();
            List<Item> accessories = new ArrayList<>();

            for (Item it : player.getInventory()) {
                switch (it.type) {
                    case CONSUMABLE -> consumables.add(it);
                    case WEAPON -> weapons.add(it);
                    case ARMOR -> armor.add(it);
                    case ACCESSORY -> accessories.add(it);
                }
            }

            Map<Integer, Item> index = new LinkedHashMap<>();
            int n = 1;

            if (consumables.isEmpty() && weapons.isEmpty() && armor.isEmpty() && accessories.isEmpty()) {
                System.out.println("\nInventory is empty...");
            } else {

                if (!consumables.isEmpty()) {
                    System.out.println("\nConsumables");
                    for (Item it : consumables) {
                        System.out.println("[" + n + "] " + it.name + " x" + it.qty);
                        index.put(n++, it);
                    }
                }

                if (!weapons.isEmpty()) {
                    System.out.println("\nWeapons");
                    for (Item it : weapons) {
                        String eq = (player.equipWeapon == it) ? " (Equipped)" : "";
                        System.out.println("[" + n + "] " + it.name + eq);
                        index.put(n++, it);
                    }
                }

                if (!armor.isEmpty()) {
                    System.out.println("\nArmor");
                    for (Item it : armor) {
                        String eq = (player.equipArmor == it) ? " (Equipped)" : "";
                        System.out.println("[" + n + "] " + it.name + eq);
                        index.put(n++, it);
                    }
                }

                if (!accessories.isEmpty()) {
                    System.out.println("\nAccessories");
                    for (Item it : accessories) {
                        String slot = "[" + it.accessoryType + "]";
                        boolean equipped =
                                player.ringSlot == it ||
                                        player.pendantSlot == it ||
                                        player.charmSlot == it;

                        String eq = equipped ? " (Equipped)" : "";

                        System.out.println("[" + n + "] " + it.name + " " + slot + eq);
                        index.put(n++, it);
                    }
                }
            }

            // MENU
            System.out.println("[V] Accessory Slots");
            System.out.println("[C] Character Stats");
            System.out.println("[M] Materials");
            System.out.println("[B] Back");
            System.out.print("Choose item number or menu option: ");

            String choice = sc.nextLine().trim().toUpperCase();

            // MENU COMMANDS
            if (choice.equals("B")) return;
            if (choice.equals("V")) { showAccessorySlots(player); continue; }
            if (choice.equals("C")) { showStats(player); continue; }
            if (choice.equals("M")) { openMaterials(player); continue; }   // <<< ADDED

            // ITEM NUMBER
            try {
                int num = Integer.parseInt(choice);
                if (index.containsKey(num)) {
                    Item selected = index.get(num);
                    openItemAction(player, selected);
                } else {
                    System.out.println("Invalid number!");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------------------------------------------------
    // MATERIAL INVENTORY (from your MaterialUI)
    // ---------------------------------------------------------
    public static void openMaterials(Player player) {
        System.out.println("\n==== MATERIAL INVENTORY ====");

        if (player.materials.isEmpty()) {
            System.out.println("You have no materials yet.");
        } else {
            int i = 1;
            for (Map.Entry<String, Integer> entry : player.materials.entrySet()) {
                System.out.println("[" + i + "] " + entry.getKey() + " x" + entry.getValue());
                i++;
            }
        }

        System.out.println("\n[B] Back");
        System.out.print("Choose: ");
        sc.nextLine();
    }

    // ---------------------------------------------------------
    // Open a specific item
    // ---------------------------------------------------------
    private static void openItemAction(Player player, Item item) {

        System.out.println("\n==== " + item.name + " ====");

        switch (item.type) {

            case CONSUMABLE -> {
                System.out.println("Consumable");
                System.out.println("+ " + item.hpRestore + " HP");
                System.out.println("+ " + item.energyRestore + " Energy");
                System.out.println("[1] Use");
                System.out.println("[2] Back");
                System.out.print("Choose: ");

                if (sc.nextLine().trim().equals("1")) {
                    InventoryManager.useConsumable(player, item);
                }
            }

            case WEAPON -> {
                System.out.println("Weapon");
                System.out.println("+ " + item.atkBonus + " ATK");
                System.out.println("[1] Equip");
                System.out.println("[2] Back");
                System.out.print("Choose: ");

                if (sc.nextLine().trim().equals("1")) {
                    InventoryManager.equipItem(player, item);
                }
            }

            case ARMOR -> {
                System.out.println("Armor");
                System.out.println("+ " + item.defBonus + " DEF");
                System.out.println("[1] Equip");
                System.out.println("[2] Back");
                System.out.print("Choose: ");

                if (sc.nextLine().trim().equals("1")) {
                    InventoryManager.equipItem(player, item);
                }
            }

            case ACCESSORY -> {
                System.out.println("Accessory (" + item.accessoryType + ")");
                System.out.println("+ " + item.atkBonus + " ATK");
                System.out.println("+ " + item.defBonus + " DEF");
                System.out.println("+ " + item.hpBonus + " Max HP");
                System.out.println("+ " + item.energyBonus + " Max Energy");
                System.out.println("[1] Equip");
                System.out.println("[2] Back");
                System.out.print("Choose: ");

                if (sc.nextLine().trim().equals("1")) {
                    InventoryManager.equipItem(player, item);
                }
            }
        }
    }

    // ---------------------------------------------------------
    // Accessory Slot Viewer
    // ---------------------------------------------------------
    private static void showAccessorySlots(Player p) {

        while (true) {
            System.out.println("\n==== ACCESSORY SLOTS ====");
            System.out.println("[1] Ring:    " + (p.ringSlot    == null ? "EMPTY" : p.ringSlot.name));
            System.out.println("[2] Pendant: " + (p.pendantSlot == null ? "EMPTY" : p.pendantSlot.name));
            System.out.println("[3] Charm:   " + (p.charmSlot   == null ? "EMPTY" : p.charmSlot.name));
            System.out.println("[4] Back");
            System.out.print("Choose: ");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> unequipSlot(p, "ring");
                case "2" -> unequipSlot(p, "pendant");
                case "3" -> unequipSlot(p, "charm");
                case "4" -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void unequipSlot(Player p, String slot) {
        if (InventoryManager.unequip(p, slot)) {
            System.out.println("Unequipped " + slot + ".");
        } else {
            System.out.println("Slot empty.");
        }
    }

    // ---------------------------------------------------------
    // Character Stats UI
    // ---------------------------------------------------------
    private static void showStats(Player p) {
        System.out.println("\n====== CHARACTER STATS ======");
        System.out.println("Class: " + p.getRole());
        System.out.println("Level: " + p.level);
        System.out.println("HP: " + p.getHP() + "/" + p.getMaxHP());
        System.out.println("Energy: " + p.getEnergy() + "/" + p.getMaxEnergy());
        System.out.println("ATK: " + p.getATK());
        System.out.println("DEF: " + p.getDEF());
        System.out.println("Coins: " + p.getCoins());
        System.out.println("\nPress ENTER to return...");
        sc.nextLine();
    }
}
