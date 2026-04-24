import java.util.Scanner;

public class QuestUI {

    private static final Scanner sc = new Scanner(System.in);

    public static void open(Player player) {

        System.out.println("\n======== QUEST LOG ========\n");

        boolean hasQuest = false;

        // MAIN QUEST — Ember Stone (Mines)
        if (player.emberQuestStarted) {
            System.out.println("[MAIN QUEST]");
            System.out.println("• Investigate the mysterious glowing stone discovered in the Mines.\n");
            hasQuest = true;
        }

        // Tessara's quest
        if (player.tessaraQuestStarted && !player.tessaraQuestCompleted) {
            System.out.println("[SIDE QUEST]");
            System.out.println("• Help Tessara investigate the corruption spreading through Sorrowglade Forest.\n");
            hasQuest = true;
        }

        // ⭐ Cassian’s Quest — Explore the Manor
        if (player.cassianQuestStarted && !player.cassianQuestCompleted) {
            System.out.println("[SIDE QUEST]");
            System.out.println("• Explore the haunted Manor at the edge of Ghost Town.\n");
            hasQuest = true;
        }

        if (!hasQuest) {
            System.out.println("You have no active quests.");
        }

        System.out.println("\nPress ENTER to return...");
        sc.nextLine();
    }
}
