import java.util.List;

public class ShopManager {

    public static void openShop(Player player, List<Item> shopInventory) {

        while (true) {
            System.out.println("\n=== SHOP ===");
            System.out.println("You have " + player.getCoins() + " coins.");

            for (int i = 0; i < shopInventory.size(); i++) {
                Item it = shopInventory.get(i);
                System.out.println("[" + (i + 1) + "] " + it.name +
                        " - Buy: " + it.buyPrice + "  Sell: " + it.sellPrice);
            }

            System.out.println("[S] Sell items");
            System.out.println("[Q] Exit shop");
            System.out.print("Choice: ");

            String in = RPGGame.sc.nextLine().trim().toLowerCase();  // ✔ FIXED

            if (in.equals("q")) return;
            if (in.equals("s")) {
                sellFromPlayer(player);
                continue;
            }

            try {
                int idx = Integer.parseInt(in) - 1;
                if (idx < 0 || idx >= shopInventory.size()) {
                    System.out.println("Invalid.");
                    continue;
                }

                Item chosen = shopInventory.get(idx);

                if (player.getCoins() < chosen.buyPrice) {
                    System.out.println("Not enough coins.");
                    continue;
                }

                player.addCoins(-chosen.buyPrice);
                InventoryManager.addItem(player, chosen.copyWithQty(1));
                System.out.println("Purchased " + chosen.name + "!");

            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void sellFromPlayer(Player player) {
        List<Item> inv = player.getInventory();
        if (inv.isEmpty()) {
            System.out.println("You have nothing to sell.");
            return;
        }

        System.out.println("\nSell which item?");
        for (int i = 0; i < inv.size(); i++) {
            Item it = inv.get(i);
            System.out.println("[" + (i + 1) + "] " + it.toString() + " (Sell: " + it.sellPrice + ")");
        }
        System.out.println("[0] Cancel");
        System.out.print("Choice: ");

        String in = RPGGame.sc.nextLine().trim(); // ✔ FIXED

        try {
            int idx = Integer.parseInt(in);
            if (idx == 0) return;
            if (idx < 1 || idx > inv.size()) {
                System.out.println("Invalid.");
                return;
            }
            Item it = inv.get(idx - 1);
            player.addCoins(it.sellPrice);
            InventoryManager.removeItem(player, it.id, 1);
            System.out.println("Sold " + it.name + " for " + it.sellPrice + " coins.");

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }
}
