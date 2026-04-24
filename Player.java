import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    // ===== Quests ====

    public boolean tessaraQuestStarted = false;
    public boolean tessaraQuestCompleted = false;

    public boolean cassianQuestStarted = false;
    public boolean cassianQuestCompleted = false;

    public boolean emberQuestStarted = false;

    // ===== INN ======
    public int lastInnMap = 1;
    public int lastInnX = 5;
    public int lastInnY = 5;

    public void setLastInn(int map, int x, int y) {
        this.lastInnMap = map;
        this.lastInnX = x;
        this.lastInnY = y;
    }

    // ===== Interactions =====
    public boolean justOpenedShop = false;
    public boolean justOpenedInn = false;
    public boolean justOpenedBlacksmith = false;

    // ===== WORLD / MAP SYSTEM =====
    protected int x;
    protected int y;
    protected int mapID;

    // ===== GAMEPLAY STATS =====
    protected String name;
    protected String role;
    protected int level = 1;
    protected int xp = 0;

    protected int hp, maxHp;
    protected int atk, defense;

    // ===== ENERGY / INVENTORY / COINS =====
    protected int maxEnergy = 50;
    protected int energy = 50;
    protected int coins = 0;

    // Normal Item Inventory
    protected List<Item> inventory = new ArrayList<>();

    // ===== MATERIAL INVENTORY =====
    public Map<String, Integer> materials = new HashMap<>();

    public void addMaterial(String name, int qty) {
        materials.put(name, materials.getOrDefault(name, 0) + qty);
    }

    public boolean removeMaterial(String name, int qty) {
        int current = materials.getOrDefault(name, 0);
        if (current < qty) return false;
        materials.put(name, current - qty);
        if (materials.get(name) == 0) materials.remove(name);
        return true;
    }

    // ===== EQUIPMENT SLOTS =====
    public Item equipWeapon = null;
    public Item equipArmor = null;

    public Item ringSlot = null;
    public Item pendantSlot = null;
    public Item charmSlot = null;


    public Player(String role, int x, int y, int mapID) {
        this.role = role;
        this.name = role;
        this.x = x;
        this.y = y;
        this.mapID = mapID;

        switch (role.toLowerCase()) {
            case "swordsman", "warrior" -> { maxHp = 120; atk = 12; defense = 8; maxEnergy = 40; }
            case "mage" -> { maxHp = 80; atk = 15; defense = 4; maxEnergy = 90; }
            case "assassin", "rogue" -> { maxHp = 90; atk = 14; defense = 5; maxEnergy = 50; }
            case "ranger" -> { maxHp = 100; atk = 11; defense = 6; maxEnergy = 60; }
            case "necromancer" -> { maxHp = 95; atk = 13; defense = 5; maxEnergy = 80; }
            default -> { maxHp = 100; atk = 50; defense = 5; maxEnergy = 50; }
        }

        hp = maxHp;
        energy = maxEnergy;
    }

    public boolean isDev() {
        return this instanceof Dev;
    }

    // ===== MOVEMENT =====
    public boolean move(char key, int width, int height) {
        int oldX = x, oldY = y;
        switch (key) {
            case 'W' -> y--;
            case 'S' -> y++;
            case 'A' -> x--;
            case 'D' -> x++;
            default -> { return false; }
        }
        if (x < 0 || x >= width || y < 0 || y >= height) {
            x = oldX; y = oldY;
            return false;
        }
        return true;
    }

    public void changeMap(int newMapID, int newX, int newY) {
        this.mapID = newMapID;
        this.x = newX;
        this.y = newY;
    }

    // ===== GETTERS / SETTERS =====
    public int getX() { return x; }
    public int getY() { return y; }
    public int getMapID() { return mapID; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public int getHP() { return hp; }

    public void setHP(int hp) { this.hp = Math.max(0, Math.min(hp, getMaxHP())); }

    // ===== ENERGY =====
    public int getEnergy() { return energy; }
    public void restoreEnergy(int amount) { energy = Math.min(getMaxEnergy(), energy + amount); }
    public void useEnergy(int amount) { energy = Math.max(0, energy - amount); }

    // ===== COINS =====
    public void addCoins(int amount) { coins += amount; if (coins < 0) coins = 0; }
    public int getCoins() { return coins; }

    // ===== INVENTORY =====
    public List<Item> getInventory() { return inventory; }
    public void addItem(Item item) { InventoryManager.addItem(this, item); }
    public void removeItem(Item item) { InventoryManager.removeItem(this, item.id, 1); }
    public boolean hasItem(String itemId) { return InventoryManager.findItem(this, itemId) != null; }

    // ===== XP / LEVEL =====
    public void gainXP(int amount) {
        xp += amount;
        if (xp >= level * 100) {
            xp -= level * 100;
            level++;
            maxHp += 10;
            atk += 2;
            defense++;
            hp = maxHp;
        }
    }

    // ===== COOLDOWNS =====
    public void tickCooldowns() {}

    // ===== EQUIPMENT CALCULATIONS =====
    public int getATK() {
        return atk
                + (equipWeapon == null ? 0 : equipWeapon.atkBonus)
                + (ringSlot == null ? 0 : ringSlot.atkBonus)
                + (pendantSlot == null ? 0 : pendantSlot.atkBonus)
                + (charmSlot == null ? 0 : charmSlot.atkBonus);
    }

    public int getDEF() {
        return defense
                + (equipArmor == null ? 0 : equipArmor.defBonus)
                + (ringSlot == null ? 0 : ringSlot.defBonus)
                + (pendantSlot == null ? 0 : pendantSlot.defBonus)
                + (charmSlot == null ? 0 : charmSlot.defBonus);
    }

    public int getMaxHP() {
        return maxHp
                + (ringSlot == null ? 0 : ringSlot.hpBonus)
                + (pendantSlot == null ? 0 : pendantSlot.hpBonus)
                + (charmSlot == null ? 0 : charmSlot.hpBonus);
    }

    public int getMaxEnergy() {
        return maxEnergy
                + (ringSlot == null ? 0 : ringSlot.energyBonus)
                + (pendantSlot == null ? 0 : pendantSlot.energyBonus)
                + (charmSlot == null ? 0 : charmSlot.energyBonus);
    }
}

/* ============================================================
                   PLAYER SUBCLASSES (SKILLS)
============================================================ */

class Swordsman extends Player {
    private int heavySlashCooldown = 0;

    public Swordsman(int x, int y, int mapID) {
        super("Swordsman", x, y, mapID);
        this.maxHp += 20;
        this.atk += 4;
        this.defense += 2;
        this.hp = maxHp;
    }

    public int swordSlash() {
        int dmg = atk + 3;
        System.out.println(name + " uses Sword Slash for " + dmg + " damage!");
        return dmg;
    }

    public boolean canUseHeavySlash() {
        return heavySlashCooldown == 0 && energy >= 10;
    }

    public int heavySlash() {
        useEnergy(10);
        int dmg = atk + 8;
        System.out.println(name + " uses Heavy Slash for " + dmg + " damage!");
        return dmg;
    }

    public void triggerHeavySlashCooldown() {
        heavySlashCooldown = 2;
    }

    @Override
    public void tickCooldowns() {
        if (heavySlashCooldown > 0) heavySlashCooldown--;
    }

    public void guard() {
        System.out.println(name + " takes a defensive stance! (Guard)");
    }
}

class Mage extends Player {
    public Mage(int x, int y, int mapID) {
        super("Mage", x, y, mapID);
        this.maxEnergy += 40;
        this.energy = maxEnergy;
    }

    public int fireball() {
        int dmg = atk + 10 + (int)(Math.random() * 6);
        System.out.println(name + " casts Fireball for " + dmg + " damage!");
        return dmg;
    }

    public int arcaneBolt() {
        useEnergy(8);
        int dmg = atk + 6 + (int)(Math.random() * 4);
        System.out.println(name + " casts Arcane Bolt for " + dmg + " damage!");
        return dmg;
    }

    public void manaShield() {
        System.out.println(name + " conjures a Mana Shield!");
    }
}

class Assassin extends Player {
    private int backstabCooldown = 0;

    public Assassin(int x, int y, int mapID) {
        super("Assassin", x, y, mapID);
        this.atk += 6;
        this.defense -= 1;
    }

    public int quickStab() {
        int dmg = atk + 4;
        System.out.println(name + " uses Quick Stab for " + dmg + " damage!");
        return dmg;
    }

    public boolean canUseBackstab() {
        return backstabCooldown == 0 && energy >= 10;
    }

    public int backstab() {
        useEnergy(10);
        boolean crit = Math.random() < 0.40;
        int dmg = atk + (crit ? 18 : 8);
        System.out.println(name + " uses Backstab" + (crit ? " (CRIT!)" : "") + " for " + dmg + " damage!");
        return dmg;
    }

    public void triggerBackstabCooldown() {
        backstabCooldown = 2;
    }

    @Override
    public void tickCooldowns() {
        if (backstabCooldown > 0) backstabCooldown--;
    }

    public void smokeDodge() {
        System.out.println(name + " uses Smoke Dodge and prepares to evade!");
    }
}

class Ranger extends Player {
    public Ranger(int x, int y, int mapID) {
        super("Ranger", x, y, mapID);
        this.maxHp += 10;
        this.atk += 3;
        this.defense += 1;
        this.hp = maxHp;
    }

    public int quickShot() {
        int dmg = atk + 3;
        System.out.println(name + " fires Quick Shot for " + dmg + " damage!");
        return dmg;
    }

    public int aimedShot() {
        useEnergy(8);
        boolean crit = Math.random() < 0.25;
        int dmg = atk + (crit ? 15 : 7);
        System.out.println(name + " uses Aimed Shot" + (crit ? " (CRIT!)" : "") + " for " + dmg + " damage!");
        return dmg;
    }

    public void quickstep() {
        System.out.println(name + " performs Quickstep and prepares to evade!");
    }
}

class Necromancer extends Player {
    public Necromancer(int x, int y, int mapID) {
        super("Necromancer", x, y, mapID);
        this.maxEnergy += 30;
        this.energy = maxEnergy;
    }

    public int drainTouch() {
        int dmg = atk + 5;
        System.out.println(name + " uses Drain Touch for " + dmg + " damage!");
        return dmg;
    }

    public int drainLife() {
        useEnergy(10);
        int dmg = atk + 10;
        int heal = dmg / 3;
        this.hp = Math.min(getMaxHP(), hp + heal);
        System.out.println(name + " drains life for " + dmg + " and heals " + heal + " HP!");
        return dmg;
    }

    public int summonWraith() {
        useEnergy(8);
        int dmg = atk + 8 + (int)(Math.random() * 5);
        System.out.println(name + " summons a Wraith for " + dmg + " damage!");
        return dmg;
    }
}


