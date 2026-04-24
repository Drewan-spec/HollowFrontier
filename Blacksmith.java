import java.util.Scanner;

public class Blacksmith {

    private static final Scanner sc = new Scanner(System.in);

    public static void openForge(Player player) {

        while (true) {
            System.out.println("\n==== BLACKSMITH ====");
            System.out.println("Coins: " + player.getCoins());
            System.out.println("Forge powerful accessories:");

            Item[] forgeItems = BlacksmithDatabase.BLACKSMITH_ITEMS;

            for (int i = 0; i < forgeItems.length; i++) {
                Item it = forgeItems[i];
                System.out.println("[" + (i + 1) + "] " + it.name +
                        " - " + it.buyPrice + " coins");
            }

            System.out.println("[" + (forgeItems.length + 1) + "] Back");
            System.out.print("Choose: ");

            String input = sc.nextLine().trim();
            int idx;

            try {
                idx = Integer.parseInt(input) - 1;
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            if (idx == forgeItems.length) {
                System.out.println("Leaving the forge...");
                return;
            }

            if (idx < 0 || idx >= forgeItems.length) {
                System.out.println("Invalid choice!");
                continue;
            }

            Item item = forgeItems[idx];

            // Check if already owned in inventory
            boolean ownsInventory = player.getInventory().stream()
                    .anyMatch(i -> i.id.equalsIgnoreCase(item.id));

            // Check if equipped in ANY accessory slot
            boolean ownsEquipped =
                    (player.ringSlot != null && player.ringSlot.id.equalsIgnoreCase(item.id)) ||
                            (player.pendantSlot != null && player.pendantSlot.id.equalsIgnoreCase(item.id)) ||
                            (player.charmSlot != null && player.charmSlot.id.equalsIgnoreCase(item.id));

            if (ownsInventory || ownsEquipped) {
                System.out.println("You already own this accessory!");
                continue;
            }

            System.out.println("\nForge " + item.name + " for " + item.buyPrice + " coins?");
            System.out.print("(Y/N): ");
            String confirm = sc.nextLine().trim().toLowerCase();

            if (!confirm.equals("y")) {
                System.out.println("Cancelled.");
                continue;
            }

            if (player.getCoins() < item.buyPrice) {
                System.out.println("Not enough coins!");
                continue;
            }

            player.addCoins(-item.buyPrice);
            InventoryManager.addItem(player, item.copyWithQty(1));

            System.out.println("You forged " + item.name + "!");
        }
    }
}
