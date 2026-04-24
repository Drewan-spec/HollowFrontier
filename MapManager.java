public class MapManager {

    public static void handleTeleport(Player player) {

        int map = player.getMapID();  // FIXED
        int x = player.getX();
        int y = player.getY();

        switch (map) {
            case 1: // Edge → Ash Hollow
                if (x == 10 && y == 5) teleportMessage(player, 2, "Ash Hollow", 1, 5);
                break;

            case 2: // Ash Hollow → Edge / Forest1 / Mines
                if (x == 0 && y == 5) teleportMessage(player, 1, "Edge", 9, 5);
                // Ash Hollow → Forest1
                if (x == 10 && y == 5) {

                    // Dev always allowed
                    if (player.isDev() || NPCManager.forestUnlocked) {

                        teleportMessage(player, 3, "Sorrowglade Forest", 1, 5, "Entering");

                        // Forest intro only plays once for normal players
                        CutScene.playForestIntro(player);

                    } else {
                        System.out.println("The path is overgrown and blocked by dense roots...");
                        return;
                    }
                }


                // Mines locked until Kestrel gives permission
                if (x == 5 && y == 10) {
                    if (!NPCManager.minesUnlocked && !(player instanceof Dev)) {
                        System.out.println("The path is blocked. Marshal Kestrel has restricted access to the Mines.");
                        return;
                    }
                    CutScene.playEliasMineEntranceCutscene(player);

                    teleportMessage(player, 4, "Mines", 5, 0, "Descending");
                }
                Enemies.spawnEnemies(4);
                CutScene.playAshHollowIntro(player);
                break;

            case 3: // Forest1 → Ash Hollow / Forest2 / Forest3 / Lake
                if (x == 0 && y == 5) teleportMessage(player, 2, "Ash Hollow", 9, 5, "Returning to");
                if (x == 10 && y == 5) teleportMessage(player, 5, "Forest2", 1, 5, "Entering");
                if (x == 5 && y == 0) teleportMessage(player, 7, "Forest3", 5, 10, "Entering");
                if (x == 5 && y == 10) teleportMessage(player, 11, "Lake", 5, 0, "Descending");
                Enemies.spawnEnemies(11);
                Enemies.spawnEnemies(7);
                Enemies.spawnEnemies(5);
                break;

            case 4: // Mines → Ash Hollow / AbandonedQuarry
                if (x == 5 && y == 0) teleportMessage(player, 2, "Ash Hollow", 5, 9, "Returning to");
                if (x == 0 && y == 5) teleportMessage(player, 28, "Old Copper Veins", 10, 5, "Entering");
                if (x == 10 && y == 5) teleportMessage(player, 29, "Rustworks Tunnels", 0, 5, "Entering");
                if (x == 5 && y == 10) teleportMessage(player, 30, "Smoldering Shafts", 5, 0, "Descending to");
                Enemies.spawnEnemies(28);
                Enemies.spawnEnemies(29);
                Enemies.spawnEnemies(30);
                break;

            case 5: // Forest2 → Forest1 / ForestCore / Forest4
                if(x==0&&y==5)teleportMessage(player,3,"Forest1",9,5,"Returning to");
                if(x==10&&y==5)teleportMessage(player,6,"ForestCore",1,5,"Entering");
                if(x==5&&y==0)teleportMessage(player,8,"Forest4",5,10,"Entering");
                Enemies.spawnEnemies(8);
                Enemies.spawnEnemies(6);
                break;

            case 6: // ForestCore → Forest2 / DarkCave / Ghost Town
                if(x==0&&y==5)teleportMessage(player,5,"Forest2",9,5,"Returning to");
                if(x==5&&y==10)teleportMessage(player,9,"DarkCave",5,0,"Descending to");
                if (player.getMapID() == 6 && x == 10 && y == 0) { // example coordinates
                    if (!NPCManager.ghostTownUnlocked && !player.isDev()) {
                        System.out.println("The path ahead is blocked by dense corruption...");
                        System.out.println("Tessara's trust is needed to pass through.");
                        return;
                    }

                    teleportMessage(player, 13, "Ghost Town", 5, 9, "Travelling to");
                    CutScene.playGhostTownIntro(player);
                }
                Enemies.spawnEnemies(5);
                Enemies.spawnEnemies(9);
                break;

            case 7: // Forest3 → Forest1 / Forest4
                if(x==5&&y==10)teleportMessage(player,3,"Forest1",5,0,"Returning to");
                if(x==10&&y==5)teleportMessage(player,8,"Forest4",1,5,"Entering");
                Enemies.spawnEnemies(8);
                break;

            case 8: // Forest4 → Forest3 / Forest2
                if(x==0&&y==5)teleportMessage(player,7,"Forest3",9,5,"Returning to");
                if(x==5&&y==10)teleportMessage(player,5,"Forest2",5,0,"Returning to");
                if(x==10&&y==5)teleportMessage(player,33,"Forest5",0,5,"Entering");

                Enemies.spawnEnemies(33);
                break;

            case 9: // DarkCave → ForestCore / CrystalChamber
                if(x==5&&y==0)teleportMessage(player,6,"ForestCore",5,9,"Ascending to");
                if(Enemies.crystalHeartBossDefeated&&x==5&&y==10){
                    if (!NPCManager.crystalchamberUnlocked && !(player instanceof Dev)){
                        System.out.println("The path is blocked. A magic barrier is restricting access to the Crystal Chambers.");
                    }
                    teleportMessage(player,10,"Crystal Chambers",5,0,"Descending to");
                    CutScene.playCrystalChamberIntro(player);
                }

                break;

            case 10: // CrystalChamber → DarkCave
                if(x==5&&y==0)teleportMessage(player,9,"DarkCave",5,9,"Ascending to");
                break;

            case 11: // Lake → Forest1
                if(x==5&&y==0)teleportMessage(player,3,"Forest1",5,9,"Ascending to");
                break;

            case 12: // AbandonedQuarry → Mines
                if(x==5&&y==0)teleportMessage(player,30,"Deep Mines",5,10,"Ascending to");
                if(Enemies.quarryBossDefeated&&x==5&&y==10)teleportMessage(player,27,"The Ember Caverns",5,0,"Descending to");
                break;


            case 13: // Ghost Town → ForestCore / The Manor / The Waste
                if (x == 0 && y == 5) teleportMessage(player, 6, "ForestCore", 9, 5, "Returning to");
                if (x == 5 && y == 10) teleportMessage(player, 14, "Outer Manor", 5, 0, "Entering");
                if (x == 10 && y == 5) teleportMessage(player, 15, "The Waste", 0, 5, "Entering");
                break;

            case 14:
                if (x == 5 && y == 10)
                    teleportMessage(player, 34, "Grand Parlor", 5, 0, "Entering");
                break;

            case 15: // The Waste → Ash Flats / The Canyons
                if (x == 5 && y == 10) teleportMessage(player, 16, "Ash Flats", 5, 0, "Descending to");
                if (x == 0 && y == 5) teleportMessage(player, 13, "Ghost Town", 10, 5, "Returning to");
                if (x == 10 && y == 5) teleportMessage(player, 18, "The Canyons", 0, 5, "Entering");
                if (x == 5 && y == 0) teleportMessage(player, 24, "Prison Entrance", 5, 10, "Ascending to");
                break;


            case 16: // Ash Flats → The Waste / Bone Fields
                if (x == 5 && y == 0) teleportMessage(player, 15, "The Waste", 5, 10, "Returning to");
                if (x == 5 && y == 10) teleportMessage(player, 17, "Bone Fields", 5, 0, "Descending to");
                break;

            case 17: // Bone Fields → Ash Flats
                if (x == 5 && y == 0) teleportMessage(player, 16, "Ash Flats", 5, 10, "Returning to");
                break;

            case 18: // The Canyons → The Waste / Canyon Heights / Canyon Depths
                if (x == 0 && y == 5) teleportMessage(player, 15, "The Waste", 10, 5, "Returning to");
                if (x == 5 && y == 0) teleportMessage(player, 19, "Canyon Heights", 5, 10, "Ascending to");
                if (x == 5 && y == 10) teleportMessage(player, 20, "Canyon Depths", 5, 0, "Descending to");
                if (x == 10 && y == 5) teleportMessage(player, 21, "Shattered Cliffs", 0, 5, "Moving to");
                break;

            case 19: // Canyon Heights → The Canyons
                if (x == 5 && y == 10) teleportMessage(player, 18, "The Canyons", 5, 0, "Descending to");
                break;

            case 20: // Canyon Depths → The Canyons
                if (x == 5 && y == 0) teleportMessage(player, 18, "The Canyons", 5, 10, "Ascending to");
                break;

            case 21: // Shattered Cliffs → The Canyons / Void Reach
                if (x == 0 && y == 5) teleportMessage(player, 18, "The Canyons", 10, 5, "Returning to");
                if (x == 10 && y == 5) teleportMessage(player, 22, "Void Reach", 0, 5, "Moving to");
                break;

            case 22: // Void Reach → Shattered Cliffs / The Fracture
                if (x == 0 && y == 5) teleportMessage(player, 21, "Shattered Cliffs", 10, 5, "Returning to");
                if (x == 10 && y == 5) teleportMessage(player, 23, "The Fracture", 0, 5, "Entering");
                break;

            case 23: // The Fracture → Void Reach
                if (x == 0 && y == 5) teleportMessage(player, 22, "Void Reach", 10, 5, "Returning to");
                break;

            case 24: // Prison Entrance → Prison / Waste
                if (x == 5 && y == 10) teleportMessage(player, 15, "The Waste", 5, 0, "Returning to");
                if (x == 5 && y == 0) teleportMessage(player, 25, "Prison", 5, 10, "Entering");
                break;

            case 25: // Prison → Prison Entrance / Prison Top
                if (x == 5 && y == 10) teleportMessage(player, 24, "Prison Entrance", 5, 0, "Returning to");
                if (x == 5 && y == 0) teleportMessage(player, 26, "Prison Top", 5, 10, "Descending to");
                break;

            case 26: // Prison Top → Prison
                if (x == 5 && y == 10) teleportMessage(player, 25, "Prison", 5, 0, "Returning to");
                break;

            case 27: // The Ember Caverns
                if (NPCManager.eliasFollowing) {
                    NPCManager.eliasFollowing = false;

                    for (NPC npc : NPCManager.npcs) {
                        if (npc.getName().equals("Elias Thorn")) {
                            npc.setMapID(-1);    // remove him from world
                            npc.setPosition(-1, -1);
                        }
                    }
                }
                CutScene.playEliasCavernBetrayal(player);

                if (x == 5 && y == 0)
                    teleportMessage(player, 12, "Abandoned Quarry", 5, 10, "Returning to");
                break;


            case 28: // Mine1 → Mines
                if (x == 10 && y == 5) teleportMessage(player, 4, "Mines", 1, 5, "Returning to");
                break;


            case 29: // Mine2 → Mines
                if (x == 0 && y == 5) teleportMessage(player, 4, "Mines", 9, 5, "Returning to");
                break;

            case 30: // Mine3 → Mines / Mine4 / Mine5 / Quarry
                if (x == 5 && y == 0) teleportMessage(player, 4, "Mines", 5, 9, "Returning to");
                if (x == 0 && y == 5) teleportMessage(player, 31, "Fungal Hollows", 10, 5, "Entering");
                if (x == 10 && y == 5) teleportMessage(player, 32, "Molten Barrens", 0, 5, "Entering");
                if (x == 5 && y == 10) teleportMessage(player, 12, "Abandoned Quarry", 5, 0, "Descending to");
                Enemies.spawnEnemies(31);
                Enemies.spawnEnemies(32);
                Enemies.spawnEnemies(12);
                break;

            case 31:
                if (x == 10 && y == 5) teleportMessage(player, 30, "Smoldering Shafts", 1, 5, "Returning to");
                break;


            case 32:
                if (x == 0 && y == 5) teleportMessage(player, 30, "Smoldering Shafts", 9, 5, "Returning to");
                break;

            case 33: // Forest5
                if (x == 0 && y == 5)
                    teleportMessage(player, 8, "Forest4", 9, 5, "Returning to");
                break;
            // 34 Grand Parlor → 14 / 35
            case 34:
                if (x == 5 && y == 0)
                    teleportMessage(player, 37, "Upper Walkways", 5, 10, "Ascending to");
                if (x == 0 && y == 5)
                    teleportMessage(player, 39, "Basement Descent", 10, 5, "Entering");
                if (x == 10 && y == 5)
                    teleportMessage(player, 35, "Study of Echoes", 0, 5, "Entering");
                if (x == 5 && y == 0)
                    teleportMessage(player, 35, "Upper Walkways", 5, 0, "Entering");
                break;

            // 35 Study of Echoes → 34
            case 35:
                if (x == 0 && y == 5)
                    teleportMessage(player, 34, "Grand Parlor", 9, 5, "Returning to");
                break;

            // 36 Guest Wing → 37
            case 36:
                if (x == 10 && y == 5)
                    teleportMessage(player, 37, "Upper Walkways", 1, 5, "Returning to");
                break;

            // 37 Upper Walkways → 42 / 34 / 36 / 38
            case 37:
                if (x == 5 && y == 10)
                    teleportMessage(player, 34, "Grand Parlor", 5, 1, "Returning to");
                if (x == 5 && y == 0)
                    teleportMessage(player, 42, "Heartroom", 5, 10, "Ascending to");
                if (x == 0 && y == 5)
                    teleportMessage(player, 36, "Guest Wing", 9, 5, "Entering");
                if (x == 10 && y == 5)
                    teleportMessage(player, 38, "Master's Chambers", 1, 5, "Entering");
                break;

            // 38 Master's Chambers → 37
            case 38:
                if (x == 0 && y == 5)
                    teleportMessage(player, 37, "Upper Walkways", 9, 5, "Returning to");
                break;

            // 39 Basement Descent → 40 / 34
            case 39:
                if (x == 0 && y == 5)
                    teleportMessage(player, 40, "Ember Furnace", 10, 5, "Entering");
                if (x == 10 && y == 5)
                    teleportMessage(player, 34, "Grand Parlor", 1, 5, "Returning to");
                break;

            // 40 Ember Furnace → 41 / 39
            case 40:
                if (x == 10 && y == 5)
                    teleportMessage(player, 39, "Basement Descent", 1, 5, "Returning to");

                if (!Enemies.furnaceBossDefeated) {
                    System.out.println("A massive heat wave forces you back. Something still stirs inside…");
                    return;
                }

                if (x == 0 && y == 5)
                    teleportMessage(player, 41, "Catacombs", 10, 5, "Entering");
                break;

            // 41 Catacombs → 40
            case 41:
                if (x == 10 && y == 5)
                    teleportMessage(player, 40, "Ember Furnace", 1, 5, "Returning to");
                break;

            // 42 Heartroom → 37
            case 42:
                if (x == 5 && y == 10)
                    teleportMessage(player, 37, "Upper Walkways", 5, 1, "Returning to");
                break;



        }
    }

    private static void teleportMessage(Player player, int mapID, String mapName, int newX, int newY) {
        teleportMessage(player, mapID, mapName, newX, newY, "Entering");
    }

    private static void teleportMessage(Player player, int mapID, String mapName, int newX, int newY, String message) {
        System.out.println(message + " " + mapName + "...");
        player.changeMap(mapID,newX,newY);   // FIXED
    }
}
