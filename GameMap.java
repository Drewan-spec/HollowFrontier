public class GameMap {

    public static final int WIDTH = 11;
    public static final int HEIGHT = 11;

    // Town maps
    private boolean isTown(int mapID) {
        return (mapID == 2 || mapID == 13 || mapID == 18);
    }

    public void drawMap(Player player) {

        int map = player.getMapID();
        System.out.println("=== " + getMapName(map) + " ===\n");

        boolean showLegend = isTown(map);

        // Prepare map rows so we can print legend on the right
        String[] mapLines = new String[HEIGHT];

        for (int y = 0; y < HEIGHT; y++) {

            StringBuilder row = new StringBuilder();

            for (int x = 0; x < WIDTH; x++) {

                // enemies
                Enemies.Enemy e = Enemies.getEnemyAt(map, x, y);
                if (e != null) { row.append("X "); continue; }

                // boss
                Enemies.Enemy boss = Enemies.getBossAt(map, x, y);
                if (boss != null) { row.append("B "); continue; }

                // player
                if (x == player.getX() && y == player.getY()) {
                    row.append("@ ");
                    continue;
                }

                // npc
                boolean isNPC = false;
                for (NPC npc : NPCManager.npcs) {
                    if (npc.getMapID() == map && npc.getX() == x && npc.getY() == y) {
                        row.append(npc.getSymbol()).append(" ");
                        isNPC = true;
                        break;
                    }
                }
                if (isNPC) continue;

                // terrain / arrows
                switch (map) {
                    case 1: // Edge
                        if (x == 10 && y == 5) row.append("> ");
                        else row.append(". ");
                        break;

                    case 2: // Ash Hollow
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5){
                            if(NPCManager.forestUnlocked) row.append("> ");
                            else row.append(". ");

                        }
                        else if (x == 5 && y == 10) {
                            if (NPCManager.minesUnlocked) row.append("v ");  // Visible only when unlocked
                            else row.append(". ");                           // Hidden (normal ground)
                        }

                        else row.append(". ");
                        break;


                    case 3:
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 4: // Mines (center)
                        if (x == 5 && y == 0) row.append("^ ");          // Ash Hollow
                        else if (x == 5 && y == 10) row.append("v ");    // Mine3
                        else if (x == 0 && y == 5) row.append("< ");     // Mine1
                        else if (x == 10 && y == 5) row.append("> ");    // Mine2
                        else row.append(". ");
                        break;


                    case 5:
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 6:
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5) {
                            if (NPCManager.ghostTownUnlocked) row.append("> ");
                            else row.append(". ");
                        }
                        else if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 7:
                        if (x == 5 && y == 10) row.append("v ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else row.append(". ");
                        break;

                    case 8:
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else row.append(". ");
                        break;

                    case 9:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (Enemies.crystalHeartBossDefeated && x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 10:
                        if (x == 5 && y == 0) row.append("^ ");
                        else row.append(". ");
                        break;

                    case 11:
                        if (x == 5 && y == 0) row.append("^ ");
                        else row.append(". ");
                        break;

                    case 12:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (Enemies.quarryBossDefeated && x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 13: // Ghost town
                        if (map == 13 && x == 5 && y == 3) {
                            row.append("S ");
                            continue;
                        }
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else if (x == 4 && y == 5) row.append("I ");
                        else if (x == 6 && y == 6) row.append("F ");
                        else row.append(". ");
                        break;

                    case 14:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 15:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else row.append(". ");
                        break;

                    case 16:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 17:
                        if (x == 5 && y == 0) row.append("^ ");
                        else row.append(". ");
                        break;

                    case 18: // The Canyons
                        if (map == 18 && x == 8 && y == 3) {
                            row.append("S ");
                            continue;
                        }
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else if (x == 3 && y == 4) row.append("I ");
                        else if (x == 6 && y == 6) row.append("F ");
                        else row.append(". ");
                        break;


                    case 19:
                        if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 20:
                        if (x == 5 && y == 0) row.append("^ ");
                        else row.append(". ");
                        break;

                    case 21:
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else row.append(". ");
                        break;

                    case 22:
                        if (x == 0 && y == 5) row.append("< ");
                        else if (x == 10 && y == 5) row.append("> ");
                        else row.append(". ");
                        break;

                    case 23:
                        if (x == 0 && y == 5) row.append("< ");
                        else row.append(". ");
                        break;

                    case 24:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 25:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 26:
                        if (x == 5 && y == 10) row.append("v ");
                        else row.append(". ");
                        break;

                    case 27:
                        if (x == 5 && y == 0) row.append("^ ");
                        else if (!NPCManager.starStolen && x == 5 && y == 5) row.append("* ");
                        else row.append(". ");
                        break;

                    case 28: // Mine1 (left of Mines)
                        if (x == 10 && y == 5) row.append("> ");  // back to Mines
                        else row.append(". ");
                        break;

                    case 29: // Mine2 (right of Mines)
                        if (x == 0 && y == 5) row.append("< ");   // back to Mines
                        else row.append(". ");
                        break;

                    case 30: // Mine3 (Deep Mines)
                        if (x == 5 && y == 0) row.append("^ ");   // back to Mines
                        else if (x == 5 && y == 10) row.append("v "); // to Quarry
                        else if (x == 0 && y == 5) row.append("< ");  // to Mine4
                        else if (x == 10 && y == 5) row.append("> "); // to Mine5
                        else row.append(". ");
                        break;

                    case 31: // Mine4 (Collapsed Tunnels)
                        if (x == 10 && y == 5) row.append("> ");  // back to Mine3
                        else row.append(". ");
                        break;


                    case 32: // Mine5 (Crystal Vein Tunnel)
                        if (x == 0 && y == 5) row.append("< ");   // back to Mine3
                        else row.append(". ");
                        break;

                    case 33:
                        // Forest5 shape
                        if (x == 0 && y == 5) row.append("< "); // exit back to Forest4
                        else row.append(". ");
                        break;

                    case 34:
                        if (x == 5 && y == 0) row.append("^ ");      // to 37
                        else if (x == 5 && y == 10) row.append("v ");
                        else if (x == 0 && y == 5) row.append("< "); // to 39
                        else if (x == 10 && y == 5) row.append("> "); // to 35
                        else row.append(". ");
                        break;

                    case 35:
                        if (x == 0 && y == 5) row.append("< "); // to 34
                        else row.append(". ");
                        break;

                    case 36:
                        if (x == 10 && y == 5) row.append("> "); // to 34
                        else row.append(". ");
                        break;

                    case 37:
                        if (x == 5 && y == 10) row.append("v ");    // to 34
                        else if (x == 5 && y == 0) row.append("^ "); // to 42
                        else if (x == 0 && y == 5) row.append("< "); // to 36
                        else if (x == 10 && y == 5) row.append("> "); // to 38
                        else row.append(". ");
                        break;

                    case 38:
                        if (x == 0 && y == 5) row.append("< "); // to 37
                        else row.append(". ");
                        break;

                    case 39:
                        if (x == 0 && y == 5) row.append("< ");   // to 40
                        else if (x == 10 && y == 5) row.append("> "); // to 34
                        else row.append(". ");
                        break;

                    case 40:
                        if (x == 0 && y == 5) row.append("< ");   // to 41
                        else if (x == 10 && y == 5) row.append("> "); // to 39
                        else row.append(". ");
                        break;

                    case 41:
                        if (x == 10 && y == 5) row.append("> "); // to 40
                        else row.append(". ");
                        break;

                    case 42:
                        if (x == 5 && y == 10) row.append("v "); // to 37
                        else row.append(". ");
                        break;

                    default:
                        row.append(". ");
                }
            }

            mapLines[y] = row.toString();
        }


        String[] legend = LegendManager.getLegendForMap(map);

        // PRINT MAP WITH OPTIONAL LEGEND
        for (int i = 0; i < HEIGHT; i++) {

            System.out.print(mapLines[i]);

            if (legend != null && i < legend.length) {
                System.out.print("   " + legend[i]);
            }

            System.out.println();
        }

    }

    public String getMapName(int mapID) {
        return switch (mapID) {
            case 1 -> "Edge";
            case 2 -> "Ash Hollow";
            case 3 -> "Sorrowglade Forest";
            case 4 -> "Mines";
            case 5 -> "Forest2";
            case 6 -> "ForestCore";
            case 7 -> "Forest3";
            case 8 -> "Forest4";
            case 9 -> "DarkCave";
            case 10 -> "CrystalChamber";
            case 11 -> "Lake";
            case 12 -> "AbandonedQuarry";
            case 13 -> "Ghost Town";
            case 14 -> "Outer Manor";
            case 15 -> "The Waste";
            case 16 -> "Ash Flats";
            case 17 -> "Bone Fields";
            case 18 -> "The Canyons";
            case 19 -> "Canyon Heights";
            case 20 -> "Canyon Depths";
            case 21 -> "Shattered Cliffs";
            case 22 -> "Void Reach";
            case 23 -> "The Fracture";
            case 24 -> "Prison Entrance";
            case 25 -> "Prison";
            case 26 -> "Prison Top";
            case 27 -> "The Caverns";
            case 28 -> "Old Copper Veins";
            case 29 -> "Rustworks Tunnels";
            case 30 -> "Smoldering Shafts";
            case 31 -> "Fungal Hollows";
            case 32 -> "Molten Barrens”";
            case 33 -> "Forest5";
            case 34 -> "Grand Parlor";
            case 35 -> "Study of Echoes";
            case 36 -> "Guest Wing";
            case 37 -> "Upper Walkways";
            case 38 -> "Master's Chambers";
            case 39 -> "Basement Descent";
            case 40 -> "Ember Furnace";
            case 41 -> "Catacombs";
            case 42 -> "Heartroom";


            default -> "Unknown";
        };
    }
}
