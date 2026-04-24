import java.util.Scanner;

public class InnInteraction {

    private static final Scanner sc = new Scanner(System.in);

    public static void checkInn(Player player) {

        // Prevent instant re-trigger
        if (player.justOpenedInn) {
            player.justOpenedInn = false;
            return;
        }

        int map = player.getMapID();
        int x = player.getX();
        int y = player.getY();

        // ASH HOLLOW — Inn at (4,3)
        if (map == 2 && x == 7 && y == 1) {
            innDialogue(player, 30); // price = 30 coins
            return;
        }

        // GHOST TOWN — Inn at (4,5)
        if (map == 13 && x == 4 && y == 5) {
            innDialogue(player, 50); // price = 50 coins
            return;
        }

        // THE CANYONS — Inn at (3,4)
        if (map == 18 && x == 3 && y == 4) {
            innDialogue(player, 80); // price = 80 coins
        }
    }



    private static void innDialogue(Player player, int price) {

        while (true) {
            System.out.println("\n=== Inn ===");
            System.out.println("Cost: " + price + " coins. You have: " + player.getCoins());
            System.out.println("[1] Pay & Rest");
            System.out.println("[2] Continue Journey");
            System.out.print("Choose: ");

            String input = sc.nextLine().trim();

            if (input.equals("1")) {

                if (player instanceof Dev) {
                    System.out.println("Dev mode: Free rest!");
                } else {
                    if (player.getCoins() < price) {
                        System.out.println("You don't have enough coins!");
                        return;
                    }
                    player.addCoins(-price);
                }

                // Save respawn location
                player.setLastInn(player.getMapID(), player.getX(), player.getY());

                // Restore stats
                player.setHP(player.getMaxHP());
                player.restoreEnergy(player.getMaxEnergy());

                player.lastInnMap = player.getMapID();
                player.lastInnX = player.getX();
                player.lastInnY = player.getY();

                System.out.println("\nYou rest at the inn...");
                System.out.println("HP and Energy fully restored!");

                player.justOpenedInn = true;
                return;
            }

            if (input.equals("2")) {
                System.out.println("You continue your journey...");
                player.justOpenedInn = true;
                return;
            }

            System.out.println("Invalid choice.");
        }
    }
}
