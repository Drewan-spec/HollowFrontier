import java.util.ArrayList;
import java.util.List;

public class BlacksmithDatabase {

    private static final List<Item> forgeItems = new ArrayList<>();

    // These are the displayed forge options
    public static final Item[] BLACKSMITH_ITEMS = {
            ItemDatabase.obsidianRing().copyWithQty(1),
            ItemDatabase.guardianCharm().copyWithQty(1),
            ItemDatabase.emberAmulet().copyWithQty(1)
    };

    static {
        // High-tier forgeables (can be expanded)
        forgeItems.add(ItemDatabase.obsidianBlade().copyWithQty(1));
        forgeItems.add(ItemDatabase.phoenixMail().copyWithQty(1));
        forgeItems.add(ItemDatabase.ringOfFury().copyWithQty(1));
        forgeItems.add(ItemDatabase.soulbinderCharm().copyWithQty(1));
    }

    public static List<Item> getForgeItems() {
        return forgeItems;
    }
}
