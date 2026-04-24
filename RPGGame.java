import java.io.IOException;
import java.util.Scanner;

public class RPGGame {

    static Scanner sc = new Scanner(System.in);
    public static Player player;
    static GameMap gameMap = new GameMap();

    public static void main(String[] args) {
        while (true) {
            NPCManager.initNPCs();

            int choice = MainMenu.open();
            if (choice == 2) return;

            CharacterManager.open();     // <--- NEW
            GameLoop.start(player, gameMap);
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Could not clear screen.");
        }
    }



}
