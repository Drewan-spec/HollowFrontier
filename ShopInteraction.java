import java.util.List;

public class ShopInteraction {

    public static void checkShop(Player player) {

        if (player.justOpenedShop) {
            player.justOpenedShop = false;
            return;
        }

        int map = player.getMapID();
        int x = player.getX();
        int y = player.getY();

        // ASH HOLLOW SHOP
        if (map == 2 && x == 5 && y == 1) {
            shopDialogue(player, ShopDatabase.ASH_HOLLOW_SHOP);
            return;
        }

        // GHOST TOWN SHOP
        if (map == 13 && x == 5 && y == 3) {
            shopDialogue(player, ShopDatabase.GHOST_TOWN_SHOP);
            return;
        }

        // CANYON SHOP
        if (map == 18 && x == 8 && y == 3) {
            shopDialogue(player, ShopDatabase.CANYON_SHOP);
        }
    }

    private static void shopDialogue(Player player, int shopID) {

        while (true) {
            System.out.println("\n=== Shop ===");
            System.out.println("[1] Enter Shop");
            System.out.println("[2] Continue Journey");
            System.out.print("Choose: ");

            String input = RPGGame.sc.nextLine().trim();

            if (input.equals("1")) {

                // ⭐ CLASS-BASED SHOP ITEMS
                Item[] items = ShopDatabase.getShop(shopID, player);

                player.justOpenedShop = true;

                ShopManager.openShop(player, List.of(items));

                return;
            }

            if (input.equals("2")) {
                System.out.println("You continue your journey...");
                player.justOpenedShop = true;
                return;
            }

            System.out.println("Invalid input.");
        }
    }
}
