public class BlacksmithInteraction {

    public static void checkBlacksmith(Player player) {

        // Prevent instant retrigger
        if (player.justOpenedBlacksmith) {
            player.justOpenedBlacksmith = false;
            return;
        }

        int map = player.getMapID();
        int x = player.getX();
        int y = player.getY();

        // Blacksmith is in ASH HOLLOW (map 2) at (6,6)
        if (map == 2 && x == 9 && y == 1) {
            openBlacksmithDialogue(player);
        }
    }

    private static void openBlacksmithDialogue(Player player) {

        while (true) {
            System.out.println("\n=== Blacksmith ===");
            System.out.println("[1] Open Forge");
            System.out.println("[2] Continue Journey");
            System.out.print("Choose: ");

            String input = RPGGame.sc.nextLine().trim();

            if (input.equals("1")) {
                player.justOpenedBlacksmith = true;
                Blacksmith.openForge(player);
                return;
            }

            if (input.equals("2")) {
                System.out.println("You continue your journey...");
                player.justOpenedBlacksmith = true;
                return;
            }

            System.out.println("Invalid choice.");
        }
    }
}
