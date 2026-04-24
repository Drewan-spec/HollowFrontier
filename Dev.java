import java.util.Scanner;

public class Dev extends Player {

    private Scanner sc = new Scanner(System.in);

    public Dev(int x, int y, int mapID) {
        super("Dev", x, y, mapID);
    }

    public void teleport() {
        System.out.print("Enter map name to teleport: ");
        String mapName = sc.nextLine().trim().toLowerCase();

        int targetMap = switch (mapName) {
            case "edge" -> 1;
            case "ash hollow" -> 2;
            case "forest1" -> 3;
            case "mines" -> 4;
            case "forest2" -> 5;
            case "forestcore" -> 6;
            case "forest3" -> 7;
            case "forest4" -> 8;
            case "darkcave" -> 9;
            case "crystalchamber" -> 10;
            case "lake" -> 11;
            case "abandonedquarry" -> 12;
            case "ghost town" -> 13;
            case "manor" -> 14;
            case "waste" -> 15;
            case "ash flats" -> 16;
            case "bone fields" -> 17;
            case "canyons" -> 18;
            case "canyon heights" -> 19;
            case "canyon depths" -> 20;
            case "shatter" -> 21;
            case "void" -> 22;
            case "fracture" -> 23;
            case "prison entrance" -> 24;
            case "prison" -> 25;
            case "prison top" -> 26;
            case "cavers" -> 27;
            default -> -1;
        };

        if (targetMap != -1) {
            changeMap(targetMap, GameMap.WIDTH / 2, GameMap.HEIGHT / 2);
            System.out.println("Teleported to " + mapName + "!");
        } else {
            System.out.println("Invalid map name!");
        }
    }
}
