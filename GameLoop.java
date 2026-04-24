import java.util.Scanner;

public class GameLoop {

    private static final Scanner sc = new Scanner(System.in);

    public static void start(Player player, GameMap gameMap) {

        while (true) {

            // ===== PLAYER HUD =====
            System.out.println("=====================================");
            System.out.println(" Class: " + player.getRole()
                    + "   |   Coins: " + player.getCoins());
            System.out.println(" HP: " + player.getHP() + "/" + player.getMaxHP()
                    + "   |   Energy: " + player.getEnergy() + "/" + player.getMaxEnergy());
            System.out.println("=====================================\n");

            // Draw map
            gameMap.drawMap(player);
            System.out.println();

            // NPC Interaction
            for (NPC npc : NPCManager.npcs) {

                boolean sameTile =
                        npc.getMapID() == player.getMapID() &&
                                npc.getX() == player.getX() &&
                                npc.getY() == player.getY();

                if (!sameTile) continue;

                if (npc.getName().equals("Marshal Kestrel")) {
                    NPCManager.interactWithMarshalKestrel(player);
                    continue;
                }

                if (npc.getName().equals("Tessara Windspire")) {
                    NPCManager.interactWithTessara(player);
                    continue;
                }

                if (npc.getName().equals("Cassian 'Ash' Vale")) {
                    NPCManager.interactWithCassian(player);
                    continue;
                }

                npc.talk();
            }


            // ===== ENEMY ENCOUNTER =====
            Enemies.Enemy enemy = Enemies.getEnemyAt(player.getMapID(), player.getX(), player.getY());
            if (enemy != null) {
                System.out.println("\n⚠ You encountered " + enemy.name + "!");
                System.out.println("Prepare for battle!\n");

                BattleIntegrator.startBattle(player, enemy);
                Enemies.removeEnemy(player.getMapID(), enemy);
            }


            // ===== BOSS ENCOUNTER =====
            Enemies.Enemy boss = Enemies.getBossAt(player.getMapID(), player.getX(), player.getY());
            if (boss != null) {
                System.out.println("\nBOSS ENCOUNTER!");
                System.out.println("You face the " + boss.name + "!");
                System.out.println("Prepare for a deadly battle!\n");

                BattleIntegrator.startBattle(player, boss);

                // ===== BOSS LOGIC =====

                // Quarry Boss
                if (boss == Enemies.quarryBoss) {
                    Enemies.quarryBossDefeated = true;
                    Enemies.quarryBoss = null;
                    CutScene.playCavernsUnlockedCutscene();
                    continue;
                }

                // Crystal Heart
                if (boss == Enemies.crystalHeartBoss) {
                    Enemies.crystalHeartBossDefeated = true;
                    Enemies.crystalHeartBoss = null;
                    NPCManager.crystalchamberUnlocked = true;
                    CutScene.playCrystalChamberUnlockedCutscene();
                    continue;
                }

                // Cyrill — Crystal Chamber boss
                if (boss == Enemies.crystalChamberBoss) {
                    Enemies.crystalChamberBossDefeated = true;
                    Enemies.crystalChamberBoss = null;
                    NPCManager.cyrillFreed = true;
                    CutScene.playCyrillFreedCutscene(player);
                    continue;

                }
                // After Cyrill is freed, spawn her as an NPC in Crystal Chamber
                if (!NPCManager.cyrillSpawnedInCavern) {
                    NPCManager.spawnCyrillInCrystalCavern();
                }

                if (boss == Enemies.furnaceBoss) {
                    Enemies.furnaceBossDefeated = true;
                    Enemies.furnaceBoss = null;

                    CutScene.playFurnaceBossDefeated();
                    continue;
                }


            }


            // ===== FOLLOWER SYSTEM =====
            NPCManager.updateEliasFollower(player);
            NPCManager.checkTessaraCyrillMeeting(player);


            // ===== AREA INTERACTIONS =====
            ShopInteraction.checkShop(player);
            InnInteraction.checkInn(player);
            BlacksmithInteraction.checkBlacksmith(player);


            // ===== MOVEMENT INPUT =====
            System.out.println("\nUse [WASD] to move | [I] Inventory | [Q] Quests | [M] Menu"
                    + (player instanceof Dev ? " | [T] Teleport" : ""));
            System.out.print("Input: ");

            String inp = sc.nextLine().trim();
            char move = inp.isEmpty() ? ' ' : Character.toUpperCase(inp.charAt(0));

            RPGGame.clearScreen();


            // Elias moves first when going up
            if (NPCManager.eliasFollowing && move == 'W') {
                NPCManager.moveEliasBeforePlayer(player);
            }


            // ===== DEV CHEATS =====

            if (player instanceof Dev && move == 'T') {
                ((Dev) player).teleport();
                continue;
            }

            if (player instanceof Dev && move == 'C') {
                System.out.print("Amount: ");
                String amountInput = sc.nextLine().trim();
                try {
                    int amount = Integer.parseInt(amountInput);
                    player.addCoins(amount);
                    System.out.println("You gained " + amount + " coins!");
                } catch (Exception e) {
                    System.out.println("Invalid number!");
                }
                continue;
            }


            // QUEST MENU
            if (move == 'Q') {
                QuestUI.open(player);
                continue;
            }

            // INVENTORY
            if (move == 'I') {
                InventoryUI.open(player);
                continue;
            }

            // RETURN TO MAIN MENU
            if (move == 'M') {
                return;
            }


            // NORMAL MOVEMENT
            boolean moved = player.move(move, GameMap.WIDTH, GameMap.HEIGHT);
            if (!moved) System.out.println("You hit a wall!");

            // DEBUG POS
            System.out.println("DEBUG: Player at (" + player.getX() + "," + player.getY()
                    + ") in map " + player.getMapID());


            // MAP TELEPORTS
            MapManager.handleTeleport(player);


            System.out.println("Position: (" + player.getX() + ", " + player.getY() + ")");
        }
    }
}
