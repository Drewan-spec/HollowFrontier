import java.util.HashMap;

public class LegendManager {

    private static final HashMap<Integer, String[]> legends = new HashMap<>();

    static {
        // ===== TOWNS =====
        legends.put(2, new String[]{
                "ASH HOLLOW:",
                "@ Player",
                "K Marshal Kestrel",
                "E Elias Thorn",
                "S Merchant",
                "I Inn",
                "F Blacksmith",
                "< Edge    > Forest1",
                NPCManager.minesUnlocked ? "v Mines" : " ",
        });


        legends.put(13, new String[]{
                "GHOST TOWN:",
                "@ Player",
                "E Elias Thorn",
                "S Shop",
                "I Inn",
                "F Blacksmith",
                "< ForestCore  v Manor  > Waste"
        });

        legends.put(18, new String[]{
                "THE CANYONS:",
                "@ Player",
                "S Shop",
                "I Inn",
                "F Blacksmith",
                "< Waste   ^ Heights   v Depths   > Cliffs"
        });

        // ===== MINES =====
        legends.put(4, new String[]{
                "MINES:",
                "@ Player",
                "X Mine Enemy",
                "< Mine1    ^ Ash Hollow    > Mine2",
                "v Deep Mines"
        });


        // ===== CAVERNS =====
        legends.put(27, new String[]{
                "THE CAVERNS:",
                "@ Player",
                "X Cavern Enemy",
                "* Ember Star",
                "^ Quarry"
        });

        legends.put(28, new String[]{
                "MINES LOWER WEST:",
                "@ Player",
                "> Mines"
        });

        legends.put(29, new String[]{
                "MINES LOWER EAST:",
                "@ Player",
                "< Mines"
        });

        legends.put(30, new String[]{
                "DEEP MINES:",
                "@ Player",
                "< Mine4   ^ Mines   > Mine5",
                "v Quarry"
        });

        legends.put(31, new String[]{
                "COLLAPSED TUNNELS:",
                "@ Player",
                "> Deep Mines"
        });

        legends.put(32, new String[]{
                "CRYSTAL VEIN TUNNEL:",
                "@ Player",
                "< Deep Mines"
        });


        legends.put(3, new String[]{
                "SORROWGLADE FOREST:",
                "@ Player",
                "T Tessara Windspire",
                "X Forest Enemy",
                "< Ash Hollow    > Forest Core"
        });

        legends.put(13, new String[]{
                "GHOST TOWN:",
                "@ Player",
                "S Shop",
                "I Inn",
                "F Blacksmith",
                "E ???",
                "< ForestCore   v Manor    > Waste"
        });



        // ===== QUARRY (dynamic) =====
        legends.put(12, null);
    }

    public static String[] getLegendForMap(int mapID) {
        if (mapID == 12) {
            return getDynamicQuarryLegend();
        }
        return legends.get(mapID);
    }

    private static String[] getDynamicQuarryLegend() {

        boolean bossSpawned = Enemies.quarryBoss != null && !Enemies.quarryBossDefeated;
        boolean bossDefeated = Enemies.quarryBossDefeated;

        if (bossSpawned) {
            return new String[]{
                    "ABANDONED QUARRY:",
                    "@ Player",
                    "X Quarry Enemy",
                    "B Titan of Stone (Boss!)",
                    "v Caverns (after boss)",
                    "^ Mines"
            };
        }

        if (bossDefeated) {
            return new String[]{
                    "ABANDONED QUARRY:",
                    "@ Player",
                    "X Quarry Enemy",
                    "✔ Titan Defeated",
                    "v Caverns (unlocked)",
                    "^ Mines"
            };
        }

        return new String[]{
                "ABANDONED QUARRY:",
                "@ Player",
                "X Quarry Enemy",
                "v Caverns (locked)",
                "^ Mines"
        };
    }

    public static void printLegend(int mapID) {
        String[] data = getLegendForMap(mapID);
        if (data == null) return;

        System.out.println("\n=== LEGEND ===");

        for (String line : data) {
            System.out.println(line);
        }

        System.out.println("================\n");
    }
}
