import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Enemies {

    // ====== UNIFIED ENEMY CLASS (WORLD + COMBAT) =======
    public static class Enemy {

        public String name;
        public int x, y;

        public int hp;
        public int maxHp;
        public int atk;
        public int def;
        public int xp;
        public int coins;

        public List<LootDrop> lootTable = new ArrayList<>();

        public Enemy(String name, int x, int y,
                     int hp, int atk, int def, int xp, int coins,
                     List<LootDrop> lootTable) {

            this.name = name;
            this.x = x;
            this.y = y;
            this.hp = hp;
            this.maxHp = hp;
            this.atk = atk;
            this.def = def;
            this.xp = xp;
            this.coins = coins;
            this.lootTable = lootTable;
        }

        public boolean isDead() { return hp <= 0; }
        public void takeDamage(int dmg) { hp = Math.max(0, hp - dmg); }
    }

    // ==========================================
    // GLOBAL ENEMY STORAGE
    // ==========================================
    private static final Random rand = new Random();

    public static Enemy quarryBoss = null;
    public static boolean quarryBossDefeated = false;

    public static Enemy crystalHeartBoss = null;
    public static boolean crystalHeartBossDefeated = false;

    public static Enemy crystalChamberBoss = null;
    public static boolean crystalChamberBossDefeated = false;

    public static Enemy furnaceBoss = null;
    public static boolean furnaceBossDefeated = false;



    // Forest5 Enemies
    public static Enemy[] forest5Enemies = new Enemy[5];
    public static Enemy[] forest2Enemies = new Enemy[5];
    public static Enemy[] forest3Enemies = new Enemy[5];
    public static Enemy[] forest4Enemies = new Enemy[5];
    public static Enemy[] lakeEnemies = new Enemy[5];
    public static Enemy[] forestCoreEnemies = new Enemy[5];  // map 6
    public static Enemy[] darkCaveEnemies   = new Enemy[5];  // map 9


    // Mines (map 4)
    public static Enemy[] minesEnemies = new Enemy[5];

    // Mine 1–5 (maps 28–32)
    public static Enemy[] mine1Enemies = new Enemy[5]; // map 28
    public static Enemy[] mine2Enemies = new Enemy[5]; // map 29
    public static Enemy[] mine3Enemies = new Enemy[5]; // map 30
    public static Enemy[] mine4Enemies = new Enemy[5]; // map 31
    public static Enemy[] mine5Enemies = new Enemy[5]; // map 32

    // Manor
    public static Enemy[] parlorEnemies = new Enemy[6];
    public static Enemy[] studyEnemies = new Enemy[6];
    public static Enemy[] guestEnemies = new Enemy[6];
    public static Enemy[] walkwayEnemies = new Enemy[6];
    public static Enemy[] chamberEnemies = new Enemy[6];
    public static Enemy[] basementEnemies = new Enemy[6];
    public static Enemy[] furnaceEnemies = new Enemy[5];
    public static Enemy[] catacombEnemies = new Enemy[6];


    // Quarry (map 12)
    public static Enemy[] quarryEnemies = new Enemy[5];

    // ========= SPAWN REGULAR ENEMIES =========
    public static void spawnEnemies(int mapId) {

        // MINES (Main, map 4)
        if (mapId == 4) {
            for (int i = 0; i < minesEnemies.length; i++) {
                String name = EnemyDatabase.MINES_ENEMIES[
                        rand.nextInt(EnemyDatabase.MINES_ENEMIES.length)
                        ];
                minesEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // MINE 1 (map 28)
        if (mapId == 28) {
            for (int i = 0; i < mine1Enemies.length; i++) {
                String name = EnemyDatabase.MINES1_ENEMIES[
                        rand.nextInt(EnemyDatabase.MINES1_ENEMIES.length)
                        ];
                mine1Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // MINE 2 (map 29)
        if (mapId == 29) {
            for (int i = 0; i < mine2Enemies.length; i++) {
                String name = EnemyDatabase.MINES2_ENEMIES[
                        rand.nextInt(EnemyDatabase.MINES2_ENEMIES.length)
                        ];
                mine2Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // MINE 3 (map 30)
        if (mapId == 30) {
            for (int i = 0; i < mine3Enemies.length; i++) {
                String name = EnemyDatabase.MINES3_ENEMIES[
                        rand.nextInt(EnemyDatabase.MINES3_ENEMIES.length)
                        ];
                mine3Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // MINE 4 (map 31)
        if (mapId == 31) {
            for (int i = 0; i < mine4Enemies.length; i++) {
                String name = EnemyDatabase.MINES4_ENEMIES[
                        rand.nextInt(EnemyDatabase.MINES4_ENEMIES.length)
                        ];
                mine4Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // MINE 5 (map 32)
        if (mapId == 32) {
            for (int i = 0; i < mine5Enemies.length; i++) {
                String name = EnemyDatabase.MINES5_ENEMIES[
                        rand.nextInt(EnemyDatabase.MINES5_ENEMIES.length)
                        ];
                mine5Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // QUARRY (map 12)
        if (mapId == 12) {
            for (int i = 0; i < quarryEnemies.length; i++) {
                String name = EnemyDatabase.QUARRY_ENEMIES[
                        rand.nextInt(EnemyDatabase.QUARRY_ENEMIES.length)
                        ];
                quarryEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        // FOREST 5 (map 33)
        if (mapId == 33) {
            for (int i = 0; i < forest5Enemies.length; i++) {
                String name = EnemyDatabase.FOREST5_ENEMIES[
                        rand.nextInt(EnemyDatabase.FOREST5_ENEMIES.length)
                        ];
                forest5Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // CRYSTAL CHAMBER (map 10)
        if (mapId == 10) {

            if (!crystalChamberBossDefeated) {
                // Spawn Cyrill at the center of the chamber
                crystalChamberBoss = EnemyDatabase.create(
                        "Cyrill Maunel, the Withered Sentinel",
                        5, 5
                );
            }
        }

        // FOREST 2 — map 5
        // FOREST 2 (map 5)
        if (mapId == 5) {
            for (int i = 0; i < forest2Enemies.length; i++) {
                String name = EnemyDatabase.FOREST2_ENEMIES[
                        rand.nextInt(EnemyDatabase.FOREST2_ENEMIES.length)
                        ];
                forest2Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        // FOREST 3 (map 7)
        if (mapId == 7) {
            for (int i = 0; i < forest3Enemies.length; i++) {
                String name = EnemyDatabase.FOREST3_ENEMIES[
                        rand.nextInt(EnemyDatabase.FOREST3_ENEMIES.length)
                        ];
                forest3Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        // FOREST 4 (map 8)
        if (mapId == 8) {
            for (int i = 0; i < forest4Enemies.length; i++) {
                String name = EnemyDatabase.FOREST4_ENEMIES[
                        rand.nextInt(EnemyDatabase.FOREST4_ENEMIES.length)
                        ];
                forest4Enemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        // LAKE (map 11)
        if (mapId == 11) {
            for (int i = 0; i < lakeEnemies.length; i++) {
                String name = EnemyDatabase.LAKE_ENEMIES[
                        rand.nextInt(EnemyDatabase.LAKE_ENEMIES.length)
                        ];
                lakeEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        // FOREST CORE (map 6)
        if (mapId == 6) {
            for (int i = 0; i < forestCoreEnemies.length; i++) {
                String name = EnemyDatabase.FORESTCORE_ENEMIES[
                        rand.nextInt(EnemyDatabase.FORESTCORE_ENEMIES.length)
                        ];
                forestCoreEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        // DARK CAVE (map 9)
        if (mapId == 9) {
            for (int i = 0; i < darkCaveEnemies.length; i++) {
                String name = EnemyDatabase.DARKCAVE_ENEMIES[
                        rand.nextInt(EnemyDatabase.DARKCAVE_ENEMIES.length)
                        ];
                darkCaveEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        if (mapId == 34) {
            for (int i = 0; i < parlorEnemies.length; i++) {
                String name = EnemyDatabase.PARLOR_ENEMIES[
                        rand.nextInt(EnemyDatabase.PARLOR_ENEMIES.length)
                        ];
                parlorEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        if (mapId == 35) {
            for (int i = 0; i < studyEnemies.length; i++) {
                String name = EnemyDatabase.STUDY_ENEMIES[
                        rand.nextInt(EnemyDatabase.STUDY_ENEMIES.length)
                        ];
                studyEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        if (mapId == 36) {
            for (int i = 0; i < guestEnemies.length; i++) {
                String name = EnemyDatabase.GUEST_ENEMIES[
                        rand.nextInt(EnemyDatabase.GUEST_ENEMIES.length)
                        ];
                guestEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

        if (mapId == 37) {
            for (int i = 0; i < walkwayEnemies.length; i++) {
                String name = EnemyDatabase.WALKWAY_ENEMIES[
                        rand.nextInt(EnemyDatabase.WALKWAY_ENEMIES.length)
                        ];
                walkwayEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        if (mapId == 38) {
            for (int i = 0; i < chamberEnemies.length; i++) {
                String name = EnemyDatabase.CHAMBER_ENEMIES[
                        rand.nextInt(EnemyDatabase.CHAMBER_ENEMIES.length)
                        ];
                chamberEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        if (mapId == 39) {
            for (int i = 0; i < basementEnemies.length; i++) {
                String name = EnemyDatabase.BASEMENT_ENEMIES[
                        rand.nextInt(EnemyDatabase.BASEMENT_ENEMIES.length)
                        ];
                basementEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        if (mapId == 40) {
            for (int i = 0; i < furnaceEnemies.length; i++) {
                String name = EnemyDatabase.FURNACE_ENEMIES[
                        rand.nextInt(EnemyDatabase.FURNACE_ENEMIES.length)
                        ];
                furnaceEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }


        if (mapId == 41) {
            for (int i = 0; i < catacombEnemies.length; i++) {
                String name = EnemyDatabase.CATACOMB_ENEMIES[
                        rand.nextInt(EnemyDatabase.CATACOMB_ENEMIES.length)
                        ];
                catacombEnemies[i] = EnemyDatabase.create(
                        name,
                        rand.nextInt(11),
                        rand.nextInt(11)
                );
            }
        }

    }




    // ========== SPAWN BOSS ==========

    public static void spawnCrystalHeartBoss() {
        if (crystalHeartBossDefeated) return;

        // Forest5 = map 33 (use your actual map ID here)
        crystalHeartBoss = EnemyDatabase.create("Crystal Heart", 5, 5);

        CutScene.playBossCutscene(RPGGame.player, crystalHeartBoss);
    }

    public static boolean allForest5EnemiesDead() {
        for (Enemy e : forest5Enemies) if (e != null) return false;
        return true;
    }

    public static boolean allQuarryEnemiesDead() {
        for (Enemy e : quarryEnemies) if (e != null) return false;
        return true;
    }


    public static void spawnQuarryBoss() {
        if (quarryBossDefeated) return;

        quarryBoss = EnemyDatabase.create("Titan of Stone", 5, 5);

        CutScene.playBossCutscene(RPGGame.player, quarryBoss);
    }

    public static Enemy getBossAt(int mapId, int x, int y) {

        // QUARRY BOSS (existing)
        if (mapId == 12 && quarryBoss != null) {
            if (quarryBoss.x == x && quarryBoss.y == y) {
                return quarryBoss;
            }
        }

        // CRYSTAL HEART (new)
        if (mapId == 33 && crystalHeartBoss != null) {
            if (crystalHeartBoss.x == x && crystalHeartBoss.y == y) {
                return crystalHeartBoss;
            }
        }

        // FURNACE BOSS
        if (mapId == 40 && furnaceBoss != null) {
            if (furnaceBoss.x == x && furnaceBoss.y == y) {
                return furnaceBoss;
            }
        }


        return null;
    }


    // ========== REMOVE ENEMY ==========

    public static void removeEnemy(int mapId, Enemy enemy) {


        if (mapId == 33) {
            for (int i = 0; i < forest5Enemies.length; i++) {
                if (forest5Enemies[i] == enemy) {
                    forest5Enemies[i] = null;
                    break;
                }
            }
            if (allForest5EnemiesDead() && !crystalHeartBossDefeated) {
                spawnCrystalHeartBoss();
            }
        }

        if (mapId == 40 && enemy == furnaceBoss) {
            furnaceBossDefeated = true;
            furnaceBoss = null;
        }


        if (mapId == 12) {
            for (int i = 0; i < quarryEnemies.length; i++) {
                if (quarryEnemies[i] == enemy) {
                    quarryEnemies[i] = null;
                    break;
                }
            }
            if (allQuarryEnemiesDead() && !quarryBossDefeated) {
                spawnQuarryBoss();
            }
        }

        if (mapId == 4) {
            for (int i = 0; i < minesEnemies.length; i++) {
                if (minesEnemies[i] == enemy) {
                    minesEnemies[i] = null;
                    break;
                }
            }
        }

        if (mapId == 28) {
            for (int i = 0; i < mine1Enemies.length; i++) {
                if (mine1Enemies[i] == enemy) {
                    mine1Enemies[i] = null;
                    break;
                }
            }
        }

        if (mapId == 29) {
            for (int i = 0; i < mine2Enemies.length; i++) {
                if (mine2Enemies[i] == enemy) {
                    mine2Enemies[i] = null;
                    break;
                }
            }
        }

        if (mapId == 30) {
            for (int i = 0; i < mine3Enemies.length; i++) {
                if (mine3Enemies[i] == enemy) {
                    mine3Enemies[i] = null;
                    break;
                }
            }
        }

        if (mapId == 31) {
            for (int i = 0; i < mine4Enemies.length; i++) {
                if (mine4Enemies[i] == enemy) {
                    mine4Enemies[i] = null;
                    break;
                }
            }
        }

        if (mapId == 32) {
            for (int i = 0; i < mine5Enemies.length; i++) {
                if (mine5Enemies[i] == enemy) {
                    mine5Enemies[i] = null;
                    break;
                }
            }
        }

        if (mapId == 33) {
            for (int i = 0; i < forest5Enemies.length; i++) {
                if (forest5Enemies[i] == enemy) {
                    forest5Enemies[i] = null;
                    break;
                }
            }
        }
    }

    // ========== GET ENEMY AT POSITION ==========

    public static Enemy getEnemyAt(int mapId, int x, int y) {
        Enemy[] list = null;

        if (mapId == 4)  list = minesEnemies;
        if (mapId == 28) list = mine1Enemies;
        if (mapId == 29) list = mine2Enemies;
        if (mapId == 30) list = mine3Enemies;
        if (mapId == 31) list = mine4Enemies;
        if (mapId == 32) list = mine5Enemies;
        if (mapId == 12) list = quarryEnemies;
        if (mapId == 33) list = forest5Enemies;
        if (mapId == 5) list = forest2Enemies;
        if (mapId == 7) list = forest3Enemies;
        if (mapId == 8) list = forest4Enemies;
        if (mapId == 11) list = lakeEnemies;
        if (mapId == 6) list = forestCoreEnemies;
        if (mapId == 9) list = darkCaveEnemies;


        if (list == null) return null;

        for (Enemy e : list) {
            if (e != null && e.x == x && e.y == y) return e;
        }
        return null;
    }
}
