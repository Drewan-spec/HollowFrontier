import java.util.*;

public class EnemyDatabase {

    // ---------------------------------------------------------
    // ENEMY STAT BLOCK + LOOT TABLE
    // ---------------------------------------------------------
    public static class EnemyStats {
        public int hp, atk, def, xp, coins;
        public List<LootDrop> loot;

        public EnemyStats(int hp, int atk, int def, int xp, int coins, List<LootDrop> loot) {
            this.hp = hp;
            this.atk = atk;
            this.def = def;
            this.xp = xp;
            this.coins = coins;
            this.loot = loot;
        }
    }

    // ---------------------------------------------------------
    // ENEMY POOLS (used for random spawns)
    // ---------------------------------------------------------

    // Main Mines (map 4)
    public static final String[] MINES_ENEMIES = {
            "Glyph Crawler",
            "Hollow Miner",
            "Burrower Beast"
    };

    // Mine 1 (map 28)
    public static final String[] MINES1_ENEMIES = {
            "Copper Gnawer",
            "Tunnel Lizard",
            "Rockmite Crawler"
    };

    // Mine 2 (map 29)
    public static final String[] MINES2_ENEMIES = {
            "Scrap-forged Vermin",
            "Geargnash Automaton",
            "Prowler Stalker"
    };

    // Mine 3 (map 30)
    public static final String[] MINES3_ENEMIES = {
            "Ember Wisp",
            "Dustborn Strider",
            "Tattered Shade"
    };

    // Mine 4 (map 31)
    public static final String[] MINES4_ENEMIES = {
            "Sludge Crawler",
            "Sporebloom Beast",
            "Venomcap Serpent"
    };

    // Mine 5 (map 32) – slightly stronger
    public static final String[] MINES5_ENEMIES = {
            "Molten Charger",
            "Shardfire Golem",
            "Lavafang Beast"
    };

    // Quarry (map 12)
    public static final String[] QUARRY_ENEMIES = {
            "Rocklurker",
            "Shard Golem",
            "Wailing Spirit"
    };

    public static final String[] FOREST5_ENEMIES = {
            "Witherroot Serpent",
            "Ember-Touched Dryad",
            "Hollow Bark Golem",
            "Redcap Reaver"
    };

    public static final String[] FOREST2_ENEMIES = {
            "Thornback Wolf",
            "Corrupted Sprite",
            "Grasping Root Horror"
    };

    public static final String[] FOREST3_ENEMIES = {
            "Ridge Stalker",
            "Gloomvine Imp",
            "Snarling Briarbeast"
    };

    public static final String[] FOREST4_ENEMIES = {
            "Twilight Dryad",
            "Banshee Vine Wraith",
            "Hollowgrove Sentinel"
    };

    public static final String[] LAKE_ENEMIES = {
            "Moonlit Serpent",
            "Drowned Wanderer",
            "Crystalfin Beast"
    };

    public static final String[] FORESTCORE_ENEMIES = {
            "Shadow Elk",
            "Rootbound Stalker",
            "Gloomvine Serpent"
    };

    public static final String[] DARKCAVE_ENEMIES = {
            "Cavern Wraith",
            "Echo Lurker",
            "Stonebreaker Beetle"
    };

    public static final String[] PARLOR_ENEMIES = {
            "Waltzing Wraith",
            "Hollow Dancer",
            "Shattered Marionette"
    };

    public static final String[] STUDY_ENEMIES = {
            "Possessed Tome",
            "Inkborne Horror"
    };

    public static final String[] GUEST_ENEMIES = {
            "Restless Guest",
            "Mirrorbound Shade"
    };

    public static final String[] WALKWAY_ENEMIES = {
            "Crawling Shadow",
            "Shrieking Raven"
    };

    public static final String[] CHAMBER_ENEMIES = {
            "Silent Attendant",
            "Cracked Reflection"
    };

    public static final String[] BASEMENT_ENEMIES = {
            "Ironbound Servant",
            "Ember Phantom"
    };

    public static final String[] FURNACE_ENEMIES = {
            "Molten Echo",
            "Furnace Spark"
    };

    public static final String[] CATACOMB_ENEMIES = {
            "Ash-Bound Skeleton",
            "Soulbound Knight"
    };

    // ---------------------------------------------------------
    // ENEMY DATABASE (STATS + LOOT)
    // ---------------------------------------------------------
    public static final HashMap<String, EnemyStats> stats = new HashMap<>();

    static {

        // ================= MAIN MINES (map 4) =================

        stats.put("Glyph Crawler",
                new EnemyStats(30, 6, 2, 10, 5, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Crawler Chitin", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Hollow Miner",
                new EnemyStats(35, 7, 3, 12, 6, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Rusty Ore", 1, 0.30),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.70)
                )));

        stats.put("Burrower Beast",
                new EnemyStats(40, 8, 4, 15, 8, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Burrower Hide", 1, 0.35),
                        new LootDrop(LootDrop.DropType.EXP_BOOST, "exp", 5, 0.25),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.40)
                )));

        // ================= MINE 1 (map 28) =================
        // Loot: Copper Ore, Gnawed Rock, Lizard Scale

        stats.put("Copper Gnawer",
                new EnemyStats(30, 6, 2, 10, 5, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Copper Ore", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Tunnel Lizard",
                new EnemyStats(32, 7, 2, 11, 5, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Lizard Scale", 1, 0.40),
                        new LootDrop(LootDrop.DropType.MATERIAL, "Gnawed Rock", 1, 0.20),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.40)
                )));

        stats.put("Rockmite Crawler",
                new EnemyStats(35, 6, 3, 12, 6, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Gnawed Rock", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        // ================= MINE 2 (map 29) =================
        // Loot: Scrap Metal, Old Gear, Prowler Fur

        stats.put("Scrap-forged Vermin",
                new EnemyStats(30, 6, 2, 10, 5, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Scrap Metal", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Geargnash Automaton",
                new EnemyStats(38, 8, 4, 14, 7, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Old Gear", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        stats.put("Prowler Stalker",
                new EnemyStats(34, 8, 2, 13, 6, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Prowler Fur", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        // ================= MINE 3 (map 30) =================
        // Loot: Ember, Dust, Old Fabric

        stats.put("Ember Wisp",
                new EnemyStats(28, 7, 1, 11, 5, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Ember", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Dustborn Strider",
                new EnemyStats(36, 7, 3, 13, 6, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Dust", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Tattered Shade",
                new EnemyStats(40, 8, 3, 15, 7, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Old Fabric", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        // ================= MINE 4 (map 31) =================
        // Loot: Slime, Spore, Venom Gland

        stats.put("Sludge Crawler",
                new EnemyStats(32, 7, 2, 11, 5, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Slime", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        stats.put("Sporebloom Beast",
                new EnemyStats(38, 8, 3, 14, 6, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Spore", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Venomcap Serpent",
                new EnemyStats(35, 9, 3, 15, 7, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Venom Gland", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));

        // ================= MINE 5 (map 32) =================
        // Stronger: Loot: Ember Shards, Hot Rocks, Molten Fragments

        stats.put("Molten Charger",
                new EnemyStats(50, 10, 4, 20, 10, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Ember Shards", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Shardfire Golem",
                new EnemyStats(60, 11, 5, 24, 12, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Molten Fragments", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Lavafang Beast",
                new EnemyStats(55, 10, 4, 22, 11, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Hot Rocks", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        // ================= QUARRY (map 12) =================

        stats.put("Rocklurker",
                new EnemyStats(55, 7, 4, 20, 10, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Stone Fragment", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        stats.put("Shard Golem",
                new EnemyStats(60, 10, 6, 25, 12, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Crystal Shard", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Wailing Spirit",
                new EnemyStats(50, 9, 3, 22, 10, List.of(
                        new LootDrop(LootDrop.DropType.KEY, "Spectral Essence", 1, 0.20),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.80)
                )));


        // ===============================
        // FOREST 5 — Sorrowglade Depths (Map 33)
        // ===============================

        // Witherroot Serpent
        stats.put("Witherroot Serpent",
                new EnemyStats(55, 14, 6, 35, 20, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Snake Fang", 1, 0.60),
                        new LootDrop(LootDrop.DropType.MATERIAL, "Rotten Bark", 1, 0.40)
                ))
        );

        // Ember-Touched Dryad
        stats.put("Ember-Touched Dryad",
                new EnemyStats(65, 16, 7, 45, 25, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Ember Sap", 1, 0.50),
                        new LootDrop(LootDrop.DropType.MATERIAL, "Fading Leaf", 1, 0.40)
                ))
        );

        // Hollow Bark Golem
        stats.put("Hollow Bark Golem",
                new EnemyStats(85, 18, 10, 55, 30, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Hard Bark", 1, 0.60),
                        new LootDrop(LootDrop.DropType.MATERIAL, "Crystal Dust", 1, 0.25)
                ))
        );

        // Redcap Reaver
        stats.put("Redcap Reaver",
                new EnemyStats(50, 20, 5, 40, 20, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Redcap Fang", 1, 0.50),
                        new LootDrop(LootDrop.DropType.MATERIAL, "Blood Moss", 1, 0.30)
                ))
        );

        // FOREST 2 (map 5)
        stats.put("Thornback Wolf",
                new EnemyStats(55, 11, 4, 24, 10, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Wolf Fang", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Corrupted Sprite",
                new EnemyStats(45, 13, 3, 26, 12, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Faint Ember Residue", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));

        stats.put("Grasping Root Horror",
                new EnemyStats(60, 10, 5, 30, 15, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Rotvine Tendril", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        // FOREST 3 (map 7)
        stats.put("Ridge Stalker",
                new EnemyStats(65, 14, 4, 32, 16, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Stalker Claw", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));

        stats.put("Gloomvine Imp",
                new EnemyStats(55, 12, 4, 28, 12, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Gloom Sap", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));

        stats.put("Snarling Briarbeast",
                new EnemyStats(75, 15, 6, 36, 18, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Briar Core", 1, 0.30),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.70)
                )));

        // FOREST 4 (map 8)
        stats.put("Twilight Dryad",
                new EnemyStats(80, 15, 7, 40, 22, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Dryad Bark", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Banshee Vine Wraith",
                new EnemyStats(70, 17, 5, 42, 20, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Wailing Petal", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));

        stats.put("Hollowgrove Sentinel",
                new EnemyStats(95, 16, 9, 48, 25, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Sentinel Core", 1, 0.30),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.70)
                )));

        // LAKE (map 11)
        stats.put("Moonlit Serpent",
                new EnemyStats(90, 18, 6, 46, 24, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Serpent Scale", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Drowned Wanderer",
                new EnemyStats(85, 17, 7, 44, 22, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Waterlogged Cloth", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));

        stats.put("Crystalfin Beast",
                new EnemyStats(100, 20, 8, 52, 30, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Crystalfin Shard", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));


        // ==========================================

        // ================= BOSSES =================

        // ==========================================

        stats.put("Titan of Stone",
                new EnemyStats(200, 15, 10, 100, 50, List.of(
                        new LootDrop(LootDrop.DropType.KEY, "Stone Core", 1, 1.00) // guaranteed
                )));

        stats.put("Crystal Heart",
                new EnemyStats(250, 20, 12,  150,  120, List.of(
                        new LootDrop(LootDrop.DropType.KEY, "Heart Shard", 1, 1.00)
                )));
        stats.put("Cyrill Maunel, the Withered Sentinel",
                new EnemyStats(220,28, 12, 150, 100, List.of(
                                new LootDrop(LootDrop.DropType.KEY, "Crystal Fragment", 1, 1.00),
                                new LootDrop(LootDrop.DropType.MATERIAL, "Shard Dust", 1, 0.70)
                )));

        stats.put("Ignivar, the Ashen Blight",
                new EnemyStats(
                        300, 22, 14, 180, 150, List.of(
                                new LootDrop(LootDrop.DropType.KEY, "Blight Core", 1, 1.00)
                )));

        //============================================

        // Grand parlor
        stats.put("Waltzing Wraith",
                new EnemyStats(45, 8, 3, 18, 9, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Spectral Silk", 1, 0.35),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.65)
                )));

        stats.put("Hollow Dancer",
                new EnemyStats(38, 7, 3, 16, 8, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Broken Dance Charm", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Shattered Marionette",
                new EnemyStats(42, 10, 2, 20, 10, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Splintered Puppet Limb", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        // Study of Echoes
        stats.put("Possessed Tome",
                new EnemyStats(30, 6, 1, 14, 6, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Cursed Page", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        stats.put("Inkborne Horror",
                new EnemyStats(50, 9, 4, 22, 12, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Blackened Ink Core", 1, 0.35),
                        new LootDrop(LootDrop.DropType.EXP_BOOST, "exp", 5, 0.20),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.45)
                )));

        // Guest Wing
        stats.put("Restless Guest",
                new EnemyStats(33, 7, 3, 14, 7, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Dusty Fabric Scrap", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        stats.put("Mirrorbound Shade",
                new EnemyStats(48, 9, 5, 20, 10, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Shattered Glass Fragment", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        // Upper Walkways
        stats.put("Crawling Shadow",
                new EnemyStats(36, 8, 2, 15, 7, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Shadow Residue", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Shrieking Raven",
                new EnemyStats(28, 7, 1, 12, 6, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Corrupted Feather", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        // Master's Chambers
        stats.put("Silent Attendant",
                new EnemyStats(55, 10, 4, 22, 12, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Tarnished Crest Badge", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Cracked Reflection",
                new EnemyStats(60, 11, 5, 25, 13, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Warped Mirror Shard", 1, 0.40),
                        new LootDrop(LootDrop.DropType.EXP_BOOST, "exp", 7, 0.20),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.40)
                )));

        // Basement Descent
        stats.put("Ironbound Servant",
                new EnemyStats(65, 12, 6, 28, 15, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Bent Gear Fragment", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Ember Phantom",
                new EnemyStats(55, 14, 4, 30, 15, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Ember Residue", 1, 0.35),
                        new LootDrop(LootDrop.DropType.EXP_BOOST, "exp", 8, 0.30),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.35)
                )));

        //Ember Furnace
        stats.put("Molten Echo",
                new EnemyStats(60, 13, 4, 28, 14, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Molten Fragment", 1, 0.45),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.55)
                )));

        stats.put("Furnace Spark",
                new EnemyStats(40, 9, 2, 18, 9, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Sparkstone", 1, 0.40),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.60)
                )));

        //Catacombs
        stats.put("Ash-Bound Skeleton",
                new EnemyStats(70, 11, 6, 30, 15, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Charred Bone", 1, 0.50),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.50)
                )));

        stats.put("Soulbound Knight",
                new EnemyStats(85, 14, 7, 35, 18, List.of(
                        new LootDrop(LootDrop.DropType.MATERIAL, "Ancient Knight Shard", 1, 0.35),
                        new LootDrop(LootDrop.DropType.EXP_BOOST, "exp", 12, 0.25),
                        new LootDrop(LootDrop.DropType.NONE, "", 0, 0.40)
                )));




    }

    // ---------------------------------------------------------
    // FACTORY METHOD (Creates an enemy instance for the world)
    // ---------------------------------------------------------
    public static Enemies.Enemy create(String name, int x, int y) {

        EnemyStats s = stats.get(name);

        // fallback enemy (basic stats, empty loot)
        if (s == null) {
            s = new EnemyStats(20, 5, 2, 5, 3, List.of());
        }

        return new Enemies.Enemy(
                name,
                x, y,
                s.hp,
                s.atk,
                s.def,
                s.xp,
                s.coins,
                s.loot
        );
    }
}
