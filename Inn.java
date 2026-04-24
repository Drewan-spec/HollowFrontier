import java.util.Scanner;

public class Inn {
    private static final Scanner sc = new Scanner(System.in);
    private static final int BASE_COST = 20; // base cost to rest

    public static void enterInn(Player player) {
        System.out.println("\nYou enter the inn. Rest restores HP and energy.");
        System.out.println("Cost: " + BASE_COST + " coins. You have: " + player.getCoins());
        System.out.println("[1] Pay and rest");
        System.out.println("[2] Leave");
        System.out.print("Choice: ");
        String in = sc.nextLine().trim();
        if (in.equals("1")) {
            if (player.getCoins() < BASE_COST) {
                System.out.println("You don't have enough coins.");
                return;
            }
            player.addCoins(-BASE_COST);
            // restore
            player.setHP(getPlayerMaxHP(player));
            player.restoreEnergy(getPlayerMaxEnergy(player));
            System.out.println("You rest and feel refreshed. HP and Energy fully restored.");
            // optional: call your save routine here if you have one
        } else {
            System.out.println("You leave the inn.");
        }
    }

    private static int getPlayerMaxHP(Player p) {
        try {
            java.lang.reflect.Field f = Player.class.getDeclaredField("maxHp");
            f.setAccessible(true);
            return (int) f.get(p);
        } catch (Exception e) {
            return 100;
        }
    }

    private static int getPlayerMaxEnergy(Player p) {
        try {
            java.lang.reflect.Field f = Player.class.getDeclaredField("maxEnergy");
            f.setAccessible(true);
            return (int) f.get(p);
        } catch (Exception e) {
            return 50;
        }
    }
}
