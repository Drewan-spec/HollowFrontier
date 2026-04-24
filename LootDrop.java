public class LootDrop {

    public enum DropType { MATERIAL, EXP_BOOST, KEY, NONE }

    public DropType type;
    public String name;
    public int amount;
    public double chance;

    public LootDrop(DropType type, String name, int amount, double chance) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.chance = chance;
    }
}
