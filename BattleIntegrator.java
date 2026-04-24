import java.util.Scanner;
import java.util.List;

public class BattleIntegrator {

    private static final Scanner sc = new Scanner(System.in);

    // ANSI (classic) colors
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";

    // Entry: pass the world-enemy object from map (Enemies.Enemy)
    public static void startBattle(Player player, Enemies.Enemy worldEnemy) {
        if (player == null || worldEnemy == null) return;

        // sync world enemy stats into local combat enemy
        CombatEnemy enemy = new CombatEnemy(worldEnemy);

        // Styled header
        System.out.println("\n" + BOLD + "--- ENCOUNTER ---" + RESET);
        System.out.println(YELLOW + enemy.name + " appears!" + RESET);
        System.out.println();

        // battle state flags
        boolean playerGuard = false;
        boolean playerEvade = false; // for smoke/quickstep
        boolean enemyGuard = false;

        // main loop (no rounds printed)
        battleLoop:
        while (player.getHP() > 0 && enemy.hp > 0) {

            // show concise status each loop
            System.out.println();
            printCombatStatus(player, enemy);
            System.out.println();

            // PLAYER TURN
            playerGuard = false;
            playerEvade = false;

            int action = showBattleMenuAndGetChoice(player, enemy);

            switch (action) {
                case 1 -> { // Primary / basic attack (class-specific)
                    int dmg = performPrimary(player, enemy);
                    if (dmg > 0) {
                        System.out.println(RED + "You dealt " + dmg + " damage!" + RESET);
                        enemy.hp = Math.max(0, enemy.hp - dmg);
                    }
                }
                case 2 -> { // Secondary / skill
                    int dmg = performSecondary(player, enemy);
                    if (dmg > 0) {
                        System.out.println(RED + "Skill dealt " + dmg + " damage!" + RESET);
                        enemy.hp = Math.max(0, enemy.hp - dmg);
                    }
                }
                case 3 -> { // Tertiary / utility (guard, evade, summon...)
                    boolean used = performTertiary(player, enemy);
                    if (!used) {
                        // default is Guard
                        playerGuard = true;
                        System.out.println(CYAN + player.getName() + " braces for incoming attacks (Guard)." + RESET);
                    } else {
                        // some tertiary actions set evade flag
                        if (player instanceof Assassin) playerEvade = true;
                    }
                }
                case 4 -> { // Use Item
                    boolean used = useItemDuringBattle(player);
                    if (!used) {
                        System.out.println("You did not use an item.");
                    }
                }
                case 5 -> { // Run attempt
                    if (attemptRun(player, enemy)) {
                        System.out.println("You fled the battle!");
                        break battleLoop;
                    } else {
                        System.out.println("You failed to run away!");
                    }
                }
                default -> {
                    System.out.println("Invalid action — you hesitate and lose your turn!");
                }
            }

            // If enemy died from player's action:
            if (enemy.hp <= 0) break;

            // tick cooldowns after player's action
            tickPlayerCooldowns(player);

            // ENEMY TURN
            System.out.println("\n" + BOLD + "--- Enemy Turn ---" + RESET);

            int enemyAction = enemyChooseAction(enemy, player);

            switch (enemyAction) {
                case 0 -> { // standard attack
                    int raw = Math.max(1, enemy.atk - player.getDEF());
                    int finalDmg = raw;
                    if (playerGuard) finalDmg = Math.max(0, raw / 2);
                    if (playerEvade && Math.random() < 0.7) {
                        System.out.println("You evaded the attack!");
                    } else {
                        player.setHP(Math.max(0, player.getHP() - finalDmg));
                        System.out.println(YELLOW + enemy.name + " attacks for " + finalDmg + " damage!" + RESET);
                    }
                }
                case 1 -> { // heavy attack
                    int raw = Math.max(1, (int)(enemy.atk * 1.4) - player.getDEF());
                    int finalDmg = playerGuard ? Math.max(0, raw / 2) : raw;
                    if (playerEvade && Math.random() < 0.5) {
                        System.out.println("You evaded the heavy attack!");
                    } else {
                        player.setHP(Math.max(0, player.getHP() - finalDmg));
                        System.out.println(YELLOW + enemy.name + " uses a heavy strike for " + finalDmg + " damage!" + RESET);
                    }
                }
                case 2 -> { // guard
                    enemyGuard = true;
                    System.out.println(CYAN + enemy.name + " braces for impact (Guard)." + RESET);
                }
                case 3 -> { // special (if any)
                    if (Math.random() < 0.6) {
                        int dmg = Math.max(1, enemy.atk / 2);
                        player.setHP(Math.max(0, player.getHP() - dmg));
                        System.out.println(YELLOW + enemy.name + " lashes out for " + dmg + " damage!" + RESET);
                    } else {
                        int heal = Math.min(enemy.maxHp - enemy.hp, 5 + (int)(Math.random() * 6));
                        enemy.hp += heal;
                        System.out.println(GREEN + enemy.name + " recovers " + heal + " HP!" + RESET);
                    }
                }
            }



            // Check death
            if (player.getHP() <= 0) {
                System.out.println();
                Effects.fadeYouDied();

                // Respawn at last inn
                player.changeMap(player.lastInnMap, player.lastInnX, player.lastInnY);
                player.setHP(player.getMaxHP());
                player.restoreEnergy(player.getMaxEnergy());

                System.out.println("You awaken...");
                System.out.println("(HP and Energy restored)");

                player.justOpenedInn = true;

                break;
            }
        } // end loop

        // POST-BATTLE

        if (player.getHP() > 0 && enemy.hp <= 0) {
            System.out.println("\n" + GREEN + BOLD + "🎉 You defeated " + enemy.name + "!" + RESET);
            int xpGain = enemy.xp;
            int coinsGain = enemy.coins;
            System.out.println(GREEN + "Gained " + xpGain + " XP and " + coinsGain + " coins." + RESET);
            giveLootRewards(player, worldEnemy);
            player.gainXP(xpGain);
            player.addCoins(coinsGain);
        } else if (player.getHP() <= 0) {
            System.out.println("\n" + RED + BOLD + "You were defeated. Respawn logic not handled here." + RESET);
            // optional: respawn or send to inn
        }
    }


    // -------------------------
    // Helper & skill routing
    // -------------------------
    private static int showBattleMenuAndGetChoice(Player player, CombatEnemy enemy){
        // integrated menu with 1..5 options
        while (true) {
            System.out.println("\nChoose action:");
            if (player instanceof Swordsman) {
                System.out.println("[1] " + BOLD + "Sword Slash" + RESET);
                System.out.println("[2] Heavy Slash (cost 10 energy, 2-turn cooldown)");
                System.out.println("[3] Guard");
                System.out.println("[4] Use Item");
                System.out.println("[5] Run");
            } else if (player instanceof Mage) {
                System.out.println("[1] " + BOLD + "Fireball" + RESET + " (cost 0)");
                System.out.println("[2] Arcane Bolt (cost 8)");
                System.out.println("[3] Mana Shield (cost 12, reduces damage)");
                System.out.println("[4] Use Item");
                System.out.println("[5] Run");
            } else if (player instanceof Assassin) {
                System.out.println("[1] " + BOLD + "Quick Stab" + RESET);
                System.out.println("[2] Backstab (cost 10, 2-turn cooldown)");
                System.out.println("[3] Smoke Dodge (cost 6)");
                System.out.println("[4] Use Item");
                System.out.println("[5] Run");
            } else if (player instanceof Ranger) {
                System.out.println("[1] " + BOLD + "Quick Shot" + RESET);
                System.out.println("[2] Aimed Shot (cost 8)");
                System.out.println("[3] Quickstep (cost 5)");
                System.out.println("[4] Use Item");
                System.out.println("[5] Run");
            } else if (player instanceof Necromancer) {
                System.out.println("[1] " + BOLD + "Drain Touch" + RESET);
                System.out.println("[2] Drain Life (cost 10)");
                System.out.println("[3] Summon Wraith (cost 8)");
                System.out.println("[4] Use Item");
                System.out.println("[5] Run");
            } else {
                System.out.println("[1] Attack");
                System.out.println("[4] Use Item");
                System.out.println("[5] Run");
            }

            System.out.print("Choice: ");
            String in = sc.nextLine().trim();

            if (in.matches("[1-5]")) return Integer.parseInt(in);
            System.out.println("Invalid input. Choose 1-5.");
        }
    }

    private static int performPrimary(Player player, CombatEnemy enemy) {
        if (player instanceof Swordsman s) return s.swordSlash();
        if (player instanceof Mage m) return m.fireball(); // mage primary is fireball
        if (player instanceof Assassin a) return a.quickStab();
        if (player instanceof Ranger r) return r.quickShot();
        if (player instanceof Necromancer n) return n.drainTouch();
        // fallback
        int dmg = Math.max(1, player.getATK());
        System.out.println(RED + player.getName() + " attacks for " + dmg + " damage!" + RESET);
        return dmg;
    }

    private static int performSecondary(Player player, CombatEnemy enemy) {
        // handle energy and cooldown checks inside subclass methods where needed
        if (player instanceof Swordsman s) {
            if (!s.canUseHeavySlash()) { System.out.println("Heavy Slash is on cooldown or insufficient energy."); return 0; }
            int dmg = s.heavySlash();
            s.triggerHeavySlashCooldown();
            return dmg;
        }
        if (player instanceof Mage m) {
            if (m.getEnergy() < 8) { System.out.println("Not enough energy for Arcane Bolt."); return 0; }
            return m.arcaneBolt();
        }
        if (player instanceof Assassin a) {
            if (!a.canUseBackstab()) { System.out.println("Backstab is on cooldown or insufficient energy."); return 0; }
            int dmg = a.backstab();
            a.triggerBackstabCooldown();
            return dmg;
        }
        if (player instanceof Ranger r) {
            if (r.getEnergy() < 8) { System.out.println("Not enough energy for Aimed Shot."); return 0; }
            return r.aimedShot();
        }
        if (player instanceof Necromancer n) {
            if (n.getEnergy() < 10) { System.out.println("Not enough energy for Drain Life."); return 0; }
            return n.drainLife();
        }
        // fallback
        return Math.max(1, player.getATK());
    }

    private static boolean performTertiary(Player player, CombatEnemy enemy) {
        if (player instanceof Swordsman s) {
            s.guard(); // just prints
            return false;
        }
        if (player instanceof Mage m) {
            if (m.getEnergy() < 12) { System.out.println("Not enough energy for Mana Shield."); return false; }
            m.manaShield();
            m.useEnergy(12); // uses energy
            return true;
        }
        if (player instanceof Assassin a) {
            if (a.getEnergy() < 6) { System.out.println("Not enough energy for Smoke Dodge."); return false; }
            a.smokeDodge();
            a.useEnergy(6);
            return true;
        }
        if (player instanceof Ranger r) {
            if (r.getEnergy() < 5) { System.out.println("Not enough energy for Quickstep."); return false; }
            r.quickstep();
            r.useEnergy(5);
            return true;
        }
        if (player instanceof Necromancer n) {
            if (n.getEnergy() < 8) { System.out.println("Not enough energy for Summon Wraith."); return false; }
            int dmg = n.summonWraith();
            enemy.hp = Math.max(0, enemy.hp - dmg);
            n.useEnergy(8);
            return true;
        }
        return false;
    }

    // attempt run (chance uses enemy.atk for difficulty)
    private static boolean attemptRun(Player player, CombatEnemy enemy) {
        double base = 0.55;
        double penalty = Math.max(0, (enemy.atk - player.getATK()) * 0.02);
        return Math.random() < (base - penalty);
    }




    private static boolean useItemDuringBattle(Player player) {
        List<Item> inv = player.getInventory();
        if (inv.isEmpty()) {
            System.out.println("You have no items.");
            return false;
        }

        System.out.println("\nInventory:");
        for (int i = 0; i < inv.size(); i++) {
            Item it = inv.get(i);
            System.out.println("[" + (i + 1) + "] " + it.name + " x" + it.qty);
        }
        System.out.println("[0] Cancel");
        System.out.print("Choose item: ");

        String sel = sc.nextLine().trim();
        int idx;
        try {
            idx = Integer.parseInt(sel);
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return false;
        }

        if (idx == 0) return false;
        if (idx < 1 || idx > inv.size()) {
            System.out.println("Invalid selection.");
            return false;
        }

        Item chosen = inv.get(idx - 1);
        if (chosen.type != Item.ItemType.CONSUMABLE) {
            System.out.println("You can't use that here.");
            return false;
        }

        if (chosen.hpRestore > 0) {
            System.out.println("You use " + chosen.name + ".");
            player.setHP(Math.min(getPlayerMaxHP(player), player.getHP() + chosen.hpRestore));
        }
        if (chosen.energyRestore > 0) {
            System.out.println("You use " + chosen.name + ".");
            player.restoreEnergy(chosen.energyRestore);
        }

        InventoryManager.removeItem(player, chosen.id, 1);
        return true;
    }


    private static void tickPlayerCooldowns(Player player) {
        // recommended: Player subclasses store cooldown counters and provide a tick method.
        try {
            java.lang.reflect.Method m = player.getClass().getMethod("tickCooldowns");
            m.invoke(player);
        } catch (Exception ignored) {}
    }

    private static int enemyChooseAction(CombatEnemy enemy, Player player) {
        // Simple AI:
        // if low hp and >30% chance to heal
        if (enemy.hp < enemy.maxHp * 0.25 && Math.random() < 0.4) return 3; // special (maybe heal)
        // if enemy strong and random -> heavy attack
        if (Math.random() < 0.15) return 1;
        // else normal attack
        return 0;
    }

    // reflectively read player's maxHP and maxEnergy (fields are protected in Player)
    private static int getPlayerMaxHP(Player player) {
        try {
            java.lang.reflect.Field f = Player.class.getDeclaredField("maxHp");
            f.setAccessible(true);
            return (int) f.get(player);
        } catch (Exception e) {
            return 100;
        }
    }

    private static int getPlayerMaxEnergy(Player player) {
        try {
            java.lang.reflect.Field f = Player.class.getDeclaredField("maxEnergy");
            f.setAccessible(true);
            return (int) f.get(player);
        } catch (Exception e) {
            return 100;
        }
    }

    // Small wrapper to copy the world enemy data into combat struct
    private static class CombatEnemy {
        String name;
        int hp;
        int maxHp;
        int atk;
        int def;
        int xp;
        int coins;

        CombatEnemy(Enemies.Enemy e) {
            this.name = e.name;
            this.hp = e.hp;
            this.maxHp = e.maxHp;
            this.atk = e.atk;
            this.def = e.def;
            this.xp = e.xp;
            this.coins = e.coins;
        }
    }

    // ---------- small helpers ----------
    private static void printCombatStatus(Player player, CombatEnemy enemy) {
        String pHp = BOLD + "HP: " + player.getHP() + RESET + " / " + getPlayerMaxHP(player);
        String pEnergy = "Energy: " + player.getEnergy() + " / " + getPlayerMaxEnergy(player);
        System.out.println(player.getName() + " " + pHp + "   " + pEnergy);
        System.out.println(enemy.name + " HP: " + enemy.hp + " / " + enemy.maxHp);
    }


    //-------------------------------------------------------
// LOOT DROP REWARDS
//-------------------------------------------------------
    private static void giveLootRewards(Player player, Enemies.Enemy enemy) {

        if (enemy.lootTable == null || enemy.lootTable.isEmpty()) {
            System.out.println("No loot table for this enemy.");
            return;
        }

        System.out.println("\n--- Loot Drops ---");

        boolean droppedSomething = false;

        for (LootDrop drop : enemy.lootTable) {

            if (Math.random() <= drop.chance) {
                droppedSomething = true;

                switch (drop.type) {

                    case MATERIAL -> {
                        player.addMaterial(drop.name, drop.amount);
                        System.out.println("You obtained: " + drop.name + " x" + drop.amount);
                    }

                    case EXP_BOOST -> {
                        player.gainXP(drop.amount);
                        System.out.println("Bonus XP gained: +" + drop.amount);
                    }

                    case KEY -> {
                        Item keyItem = ItemDatabase.getItem(drop.name);

                        if (keyItem != null) {
                            InventoryManager.addItem(player, keyItem.copyWithQty(drop.amount));
                            System.out.println("You obtained a key item: " + keyItem.name);
                        } else {
                            System.out.println("ERROR: Key item '" + drop.name + "' does not exist in ItemDatabase!");
                        }
                    }

                    case NONE -> {
                        // do nothing
                    }
                }
            }
        }

        if (!droppedSomething) {
            System.out.println("Nothing dropped...");
        }
    }



}
