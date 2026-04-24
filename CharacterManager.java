import java.util.Scanner;

public class CharacterManager {

    private static final Scanner sc = new Scanner(System.in);

    // ======================================================
    // MAIN ENTRY — Called from RPGGame
    // ======================================================
    public static void open() {

        while (true) {
            RPGGame.clearScreen();
            System.out.println("=== CHOOSE YOUR CLASS ===\n");

            System.out.println("[1] Swordsman");
            System.out.println("[2] Mage");
            System.out.println("[3] Assassin");
            System.out.println("[4] Ranger");
            System.out.println("[5] Necromancer\n");


            System.out.print("Pick role (1-6): ");
            String input = sc.nextLine().trim();

            int id = parseIntSafe(input);

            if (id < 1 || id > 6) {
                System.out.println("Invalid class. Press ENTER to continue...");
                sc.nextLine();
                continue;
            }

            String name = getClassName(id);
            String desc = getDescription(id);

            // Preview + Confirm
            if (previewClass(name, desc, id)) return;
        }
    }

    // ======================================================
    // CLASS PREVIEW SCREEN
    // ======================================================
    private static boolean previewClass(String name, String description, int id) {
        RPGGame.clearScreen();

        System.out.println("=== " + name.toUpperCase() + " ===\n");
        System.out.println(description + "\n");

        System.out.println("[1] Confirm");
        System.out.println("[2] Back");

        System.out.print("Choose: ");
        String choice = sc.nextLine().trim();

        if (choice.equals("1")) {
            createPlayer(id);
            return true;
        }

        return false;
    }

    // ======================================================
    // PLAYER CREATION
    // ======================================================
    private static void createPlayer(int id) {

        int startMap = 1;
        int sx = GameMap.WIDTH / 2;
        int sy = GameMap.HEIGHT / 2;

        switch (id) {
            case 1 -> RPGGame.player = new Swordsman(sx, sy, startMap);
            case 2 -> RPGGame.player = new Mage(sx, sy, startMap);
            case 3 -> RPGGame.player = new Assassin(sx, sy, startMap);
            case 4 -> RPGGame.player = new Ranger(sx, sy, startMap);
            case 5 -> RPGGame.player = new Necromancer(sx, sy, startMap);
            case 6 -> RPGGame.player = new Dev(sx, sy, startMap);
        }

        RPGGame.clearScreen();
        System.out.println("You have chosen: " + RPGGame.player.getRole());
        System.out.println("Press ENTER to begin your journey...");
        sc.nextLine();
    }

    // ======================================================
    // CLASS NAMES
    // ======================================================
    private static String getClassName(int id) {
        return switch (id) {
            case 1 -> "Swordsman";
            case 2 -> "Mage";
            case 3 -> "Assassin";
            case 4 -> "Ranger";
            case 5 -> "Necromancer";
            case 6 -> "Dev";
            default -> "Unknown";
        };
    }

    // ======================================================
    // CLASS DESCRIPTIONS
    // ======================================================
    private static String getDescription(int id) {
        return switch (id) {
            case 1 -> "A balanced warrior with high durability and strong melee combat.\nReliable in any situation.";
            case 2 -> "A master of arcane power.\nLow defense but extreme magic potential.";
            case 3 -> "A swift killer specializing in critical hits and burst damage.\nFragile but deadly.";
            case 4 -> "A ranged specialist with strong precision and mobility.\nBalanced offense and defense.";
            case 5 -> "A manipulator of spirits and death.\nLife-steal abilities but low durability.";
            case 6 -> "Debug-only class with teleportation and special developer commands.";
            default -> "Unknown class.";
        };
    }

    // ======================================================
    // UTILITY
    // ======================================================
    private static int parseIntSafe(String s) {
        try { return Integer.parseInt(s); }
        catch (Exception e) { return -1; }
    }
}
