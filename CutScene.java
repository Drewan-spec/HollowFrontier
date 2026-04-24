import java.util.Scanner;

public class CutScene {



    // Wait function for hard pauses
    private static void pause(int ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // NEW — Forces player to finish the cutscene before gameplay returns
    private static void waitForEnter() {
        System.out.println("\n(Press Enter to continue)");
        RPGGame.sc.nextLine();
    }

    public static void slowPrint(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(delay); } catch (Exception ignored) {}
        }
        System.out.println();
    }


    public static void playMarshalKestrelCutscene(Player player) {

        if (NPCManager.marshalCutscenePlayed) {
            slowPrint("\nMarshal Kestrel: \"Back again? Keep your eyes open out there.\"", 30);
            waitForEnter();
            return;
        }
        NPCManager.marshalCutscenePlayed = true;

        System.out.println("\n--- MARSHAL KESTREL ---\n");

        slowPrint("\"You’re the newcomer, right? Good. I need someone capable.\"", 35);
        pause(700);

        // If Ember Stone already stolen
        if (NPCManager.starStolen) {

            slowPrint("\"The stone… it’s gone. Stolen right under our noses.\"", 35);
            pause(600);
            slowPrint("\"We’re in trouble now. Keep your head low and report anything strange.\"", 35);

            if (player.emberQuestStarted) {
                player.emberQuestStarted = false;
                slowPrint("\n(Your quest has been removed — the Ember Stone is already gone.)", 25);
            }

            waitForEnter();
            return;
        }

        // If quest NOT yet started
        if (!player.emberQuestStarted) {

            slowPrint("\"A stone was found deep in the mines. Not just any stone — this one glows.\"", 35);
            pause(800);
            slowPrint("\"Miners swear it pulses… like it’s alive.\"", 35);
            pause(700);

            slowPrint("\n\"I need someone to go down there and confirm what exactly they found.\"", 35);
            slowPrint("\"No panic. No rumors. Just the truth.\"", 35);

            pause(700);

            // ===== ACCEPT OR DENY QUEST =====
            System.out.print("\nAccept quest 'Investigate the Mysterious Stone in the Mines'? [Y/N]: ");
            String ans = RPGGame.sc.nextLine().trim().toUpperCase();

            while (!ans.equals("Y") && !ans.equals("N")) {
                System.out.print("Invalid. Enter Y or N: ");
                ans = RPGGame.sc.nextLine().trim().toUpperCase();
            }

            if (ans.equals("Y")) {
                player.emberQuestStarted = true;
                slowPrint("\n--- QUEST ACCEPTED ---", 0);
                slowPrint("Investigate the strange glowing stone found in the Mines.", 0);
                player.emberQuestStarted = true;
                NPCManager.minesUnlocked = true;
            } else {
                slowPrint("\nKestrel: \"If you change your mind, I'll be right here.\"", 35);
            }

            waitForEnter();
            return;
        }

        // If quest already active
        slowPrint("\nKestrel nods.", 35);
        slowPrint("\"The mines are waiting. Find that stone and report back.\"", 35);
        waitForEnter();
    }

    // ============================
    // ELIAS CUTSCENE (ENTERING THE MINES)
    // ============================

    public static void playEliasMineEntranceCutscene(Player player) {

        if (NPCManager.eliasMineCutscenePlayed) return;
        NPCManager.eliasMineCutscenePlayed = true;

        System.out.println("\n--- CUTSCENE ---\n");

        slowPrint("As you approach the mine entrance, someone steps out from behind a crate.", 35);
        pause(700);
        slowPrint("Elias Thorn: \"Hey! There you are. Figured you'd be heading this way.\"", 35);
        pause(700);

        slowPrint("\"Heard about the glowing stone they dug up. Sounds dangerous… but also\"—", 35);
        slowPrint("he pauses, eyes gleaming—\"interesting.\"", 35);
        pause(700);

        slowPrint("\nElias: \"I can help you explore the place. Two sets of eyes are better than one, right?\"", 35);
        pause(700);

        slowPrint("\nHe steps aside, gesturing toward the dark mine shaft.", 35);
        slowPrint("\"Let’s check it out together. Just… stay close.\"", 0);

        waitForEnter();
        NPCManager.eliasFollowing = true;

    }

    // ============================
    // ELIAS BETRAYAL CUTSCENE
    // ============================
    public static void playEliasCavernBetrayal(Player player) {

        // Only plays once
        if (NPCManager.eliasThornCutscenePlayed) return;
        NPCManager.eliasThornCutscenePlayed = true;

        System.out.println("\n--- CUTSCENE: The Ember Caverns ---\n");

        slowPrint("The air grows hot… unbearably hot…", 35);
        pause(800);
        slowPrint("You and Elias step deeper into the cavern.", 35);
        pause(800);

        slowPrint("\nAt the heart of the cavern lies an ancient stone altar.", 35);
        slowPrint("Floating above it… the Ember Star — a burning red jewel pulsing like a heartbeat.", 35);
        pause(1200);

        slowPrint("\nYou step closer.", 35);
        pause(500);
        slowPrint("Player: \"So this is it…\"", 35);
        pause(700);

        slowPrint("\nElias: \"Yeah… this is it.\"", 35);
        pause(900);

        slowPrint("\nSomething changes.", 35);
        slowPrint("His voice — cold. Sharp. Final.", 35);
        pause(1000);

        slowPrint("\nBefore you react—", 35);
        slowPrint("Elias shoves you hard.", 35);
        pause(600);

        slowPrint("\nYou hit the ground.", 35);
        pause(700);

        slowPrint("Elias reaches for the Ember Star with both hands…", 35);
        pause(1000);

        slowPrint("A violent surge of flaming energy erupts around him!", 35);
        pause(1200);

        slowPrint("\nElias (shouting):", 35);
        slowPrint("\"All my life I had NOTHING!\"", 40);
        pause(700);
        slowPrint("\"Everyone else was born with something — power… influence… wealth…\"", 40);
        pause(900);
        slowPrint("\"But not me. NOT ANYMORE.\"", 40);
        pause(1000);

        slowPrint("\nA crack splits the cavern floor.", 35);
        slowPrint("Rumbling. Screaming stone. The cavern begins to collapse.", 35);
        pause(1000);

        slowPrint("\nElias turns away — the Ember Star blazing in his hands.", 35);
        slowPrint("He doesn’t look back.", 35);
        pause(900);

        slowPrint("\nAs everything comes crashing down, the last thing you see is Elias escaping.", 40);
        slowPrint("…leaving you buried in the dark.", 0);
        pause(1200);

        waitForEnter();

        playKestrelRescueCutscene(player);

    }

    // ============================
    // RESCUE CUTSCENE
    // ============================

    public static void playKestrelRescueCutscene(Player player) {

        if (NPCManager.kestrelRescueCutscenePlayed) return;
        NPCManager.kestrelRescueCutscenePlayed = true;

        System.out.println("\n--- CUTSCENE ---\n");

        slowPrint("Darkness...", 35);
        pause(900);
        slowPrint("A muffled voice echoes somewhere above you.", 35);
        slowPrint("\"I FOUND HIM! He’s here! Move those rocks!\"", 35);
        pause(1000);

        slowPrint("\nYou gasp as cold air rushes into your lungs.", 35);
        slowPrint("Hands pull you free from the broken stone.", 35);
        pause(900);

        slowPrint("\nMarshal Kestrel kneels beside you, dust covering his coat.", 35);
        slowPrint("\"Easy now… you’re safe. We dug you out just in time.\"", 35);
        pause(1200);

        slowPrint("\nAfter catching your breath, you explain everything.", 35);
        pause(1000);

        slowPrint("\nKestrel listens silently… then lets out a long, heavy sigh.", 35);
        pause(900);

        slowPrint("\nMarshal Kestrel:", 35);
        slowPrint("\"Something came up while I was digging for answers… literally.\"", 35);
        pause(900);

        slowPrint("He holds up an old scripture, edges torn and burned.", 35);
        pause(800);

        slowPrint("\"This fell out from one of my bibles while I searched for clues.\"", 35);
        slowPrint("\"The Ember Star… it’s something not to be tampered with.\"", 35);
        pause(1100);

        slowPrint("\n\"It swallows your humanity. Your sanity.\"", 40);
        slowPrint("\"Twists the mind… corrupts the soul…\"", 40);
        slowPrint("\"And grants a power that distorts everything around it.\"", 40);
        pause(1400);

        slowPrint("\nKestrel looks away, jaw tight, eyes dark with worry.", 35);

        slowPrint("\n\"If Elias has it…\"", 40);
        pause(500);
        slowPrint("\"He’ll tear Dustveil apart trying to demand the justice the world denied him.\"", 40);
        pause(1300);

        slowPrint("\nHe rises, offering you a hand.", 35);

        slowPrint("\n\"The stone is loose. Elias is gone.\"", 40);
        slowPrint("He turns toward the horizon — the wind howling through Ash Hollow.", 35);

        pause(800);
        slowPrint("\n\"The hunt begins.\"", 0);

        waitForEnter();

        player.changeMap(2, 7, 2);
        playAshHollowInnWakeup(player);

    }

    // ============================
    // INN CUTSCENE(AFTER RESCUE)
    // ============================

    public static void playAshHollowInnWakeup(Player player) {

        if (NPCManager.ashHollowInnWakeupPlayed) return;
        NPCManager.ashHollowInnWakeupPlayed = true;

        System.out.println("\n--- CUTSCENE: Awakening in Ash Hollow Inn ---\n");

        slowPrint("You slowly open your eyes…", 35);
        pause(700);
        slowPrint("The smell of wood, old dust, and warm lantern light fills the room.", 35);
        pause(800);

        slowPrint("\nInnkeeper: \"Oh! You're awake!\"", 35);
        pause(700);

        slowPrint("She rushes over, relief washing across her face.", 35);
        pause(700);

        slowPrint("\nInnkeeper: \"Marshal Kestrel brought you in.\"", 35);
        slowPrint("“Said they dug you out of some collapsed cavern.”", 35);
        pause(1000);

        slowPrint("\nShe lightly taps the dust off your coat.", 35);
        slowPrint("\"Rest easy. You're safe now.\"", 35);
        pause(900);

        slowPrint("\nShe gestures toward the stairs leading outside.", 35);
        slowPrint("\"Kestrel said to look for him when you’re ready.\"", 35);
        pause(1200);

        NPCManager.forestUnlocked = true;
        waitForEnter();
    }

    // ============================
    // ASH HOLLOW INTRO
    // ============================
    public static void playAshHollowIntro(Player player) {
        if (player.isDev()) {
            System.out.println("[Cutscene skipped - Dev Mode]");
            return;
        }
        if (NPCManager.ashHollowCutscenePlayed) return;
        NPCManager.ashHollowCutscenePlayed = true;

        System.out.println("\n!!! CUTSCENE: Entering Ash Hollow !!!\n");
        pause(1200);

        slowPrint("The desert wind howls as you cross the rusted boundary marker…", 35);
        pause(1000);
        slowPrint("Beyond it lies Ash Hollow — a frontier town clinging to life in the heart of Dustveil.", 35);
        pause(1200);

        slowPrint("\nHeat shimmers above cracked rooftops.", 35);
        slowPrint("You hear merchants arguing, metal striking anvils, and distant gunshots echoing across the dunes.", 35);
        pause(1200);

        slowPrint("\nA mix of wanderers, scavengers, and outlaws pass by without a second glance.", 35);
        pause(900);
        slowPrint("Wooden signs creak overhead: ‘General Goods’, ‘Ironworks’, ‘The Broken Lantern Inn’...", 35);
        pause(1200);

        slowPrint("\nDespite the noise, a strange tension hangs in the air.", 35);
        slowPrint("People whisper about disappearances, strange lights under the earth, and a crystal burning like a dying star.", 35);
        pause(1200);

        slowPrint("\nThis is Ash Hollow.", 35);
        slowPrint("A town on the edge of civilization — and on the verge of something far greater.", 35);
        pause(1200);

        System.out.println();
        waitForEnter();
    }

    // ============================
    // TESSARA QUEST CUTSCENE
    // ============================
    public static void playTessaraIntro(Player player) {

        // Dev choice to skip/play
        if (devSkipCheck(player, "Tessara Quest Intro")) return;

        // If already did full cutscene, show short dialogue
        if (NPCManager.tessaraCutscenePlayed) {
            slowPrint("\nTessara: \"The forest still calls. Don’t lose your resolve.\"", 35);
            waitForEnter();
            return;
        }
        NPCManager.tessaraCutscenePlayed = true;

        System.out.println("\n--- CUTSCENE: Tessara Windspire ---\n");
        pause(800);

        slowPrint("A tense stillness settles over the clearing…", 35);
        pause(700);
        slowPrint("You hear the soft creak of a bowstring tightening.", 35);
        pause(900);

        slowPrint("\nA tall elven woman steps out from behind a twisted tree.", 35);
        slowPrint("Emerald eyes sharp. Cloak made of living leaves.", 35);
        pause(1200);

        slowPrint("\nTessara: \"Hold. I am Tessara Windspire, warden of these woods.\"", 35);
        pause(900);
        slowPrint("\"And you… you walk into Sorrowglade at a dangerous time.\"", 35);
        pause(1200);

        slowPrint("\nShe glances toward a patch of dead roots pulsing faintly with red light.", 35);
        pause(1200);

        slowPrint("\nTessara: \"The forest is sick.\"", 35);
        pause(700);
        slowPrint("\"A corruption grows beneath the soil — feeding, spreading, hungering.\"", 35);
        pause(1100);

        slowPrint("\nShe lowers her bow completely now, studying you not as a threat…", 35);
        slowPrint("…but as a potential ally.", 35);
        pause(1200);

        slowPrint("\nTessara: \"I cannot face this alone.\"", 35);
        slowPrint("\"The corruption is tied to something ancient — something powerful.\"", 35);
        pause(1300);

        slowPrint("\nTessara: \"Will you help me restore balance to Sorrowglade?\"", 35);
        pause(900);

        // ============================
        // CHOICE: Accept or Decline
        // ============================
        System.out.println("\nAccept quest 'Warden of the Wilds'? [Y/N]: ");
        String ans = RPGGame.sc.nextLine().trim().toUpperCase();

        while (!ans.equals("Y") && !ans.equals("N")) {
            System.out.print("Invalid. Enter Y or N: ");
            ans = RPGGame.sc.nextLine().trim().toUpperCase();
        }

        if (ans.equals("Y")) {
            player.tessaraQuestStarted = true;

            slowPrint("\n--- QUEST ACCEPTED ---", 0);
            slowPrint("Help Tessara investigate the corruption spreading through Sorrowglade.", 0);

            pause(1000);
            slowPrint("\nTessara: \"Good. Then we begin our hunt.\"", 35);
            slowPrint("\"Start by heading deeper into the forest. Something stirs near the old grove.\"", 35);

        } else {
            slowPrint("\nTessara: \"Then tread carefully. The forest does not forgive hesitation.\"", 35);
        }

        waitForEnter();
    }

    // ============================
    // TESSARA QUEST (COMPLETE) CUTSCENE
    // ============================
    public static void playTessaraCyrillReunion(Player player) {

        System.out.println("\n--- CUTSCENE: Tessara & Cyrill ---\n");

        slowPrint("The grove is unnaturally quiet as you step inside…", 35);
        pause(700);

        slowPrint("Tessara stands beneath the ancient heartwood tree, bow lowered but tense.", 35);
        pause(900);

        slowPrint("A faint blue shimmer ripples through the air behind you.", 35);
        pause(900);

        slowPrint("Cyrill steps forward — calm, whole, no longer twisted by corruption.", 35);
        pause(1100);

        slowPrint("\nTessara: \"Cyrill…?\"", 35);
        slowPrint("Her voice breaks — disbelief mixing with relief.", 35);
        pause(1100);

        slowPrint("\nCyrill: \"I’m here, Tessara. Thanks to them… I’m finally myself again.\"", 35);
        pause(1200);

        slowPrint("\nTessara lowers her bow, her hands trembling.", 35);
        slowPrint("\"I searched for you for months — praying you still lived beneath all that corruption.\"", 35);
        pause(1300);

        slowPrint("\nCyrill gently places a hand on Tessara’s shoulder.", 35);
        slowPrint("\"The Ember Star twisted me… but it couldn’t break what we built.\"", 35);
        pause(1300);

        slowPrint("\nCyrill turns to you:", 35);
        slowPrint("\"You saved my mind… and the forest’s heart. For that, I owe you everything.\"", 35);
        pause(1300);

        slowPrint("\nTessara steps toward you, expression softened but determined.", 35);
        slowPrint("\"You’ve earned my trust — more than anyone else has in a very long time.\"", 35);
        pause(1100);

        slowPrint("She looks toward the desert horizon, jaw tightening.", 35);
        pause(900);

        slowPrint("\nTessara: \"Elias carries a power that no mortal should wield.\"", 35);
        slowPrint("\"If he continues down this path… Dustveil will burn.\"", 35);
        pause(1200);

        slowPrint("\nShe places a hand over her chest — a silent vow.", 35);
        slowPrint("\"You will not face him alone. I will help you stop him.\"", 40);
        pause(1300);

        slowPrint("\nThe grove brightens — corruption fully purged.", 35);
        pause(900);

        slowPrint("\n--- QUEST COMPLETE: Warden of the Wilds ---", 0);
        slowPrint("Reward: Tessara’s Trust", 0);
        slowPrint("Reward: +250 Coins", 0);

        // GIVE PLAYER THE COINS
        player.addCoins(250);

        NPCManager.ghostTownUnlocked = true;
        System.out.println("\n(New paths have opened… you can now travel to GHOST TOWN.)");

        waitForEnter();
    }



    // ============================
    // FOREST INTRO
    // ============================
    public static void playForestIntro(Player player) {

        // Allow Dev to choose to skip or play
        if (devSkipCheck(player, "Forest Intro")) return;

        if (NPCManager.forestIntroCutscenePlayed) return;
        NPCManager.forestIntroCutscenePlayed = true;

        System.out.println("\n!!! CUTSCENE: Entering Sorrowglade Forest !!!\n");
        pause(1200);

        slowPrint("The moment you step beneath the treeline… the world changes.", 35);
        pause(1000);
        slowPrint("The air grows still — too still — as if the forest itself is holding its breath.", 35);
        pause(1200);

        slowPrint("\nLeaves rustle without wind.", 35);
        slowPrint("Flowers bloom… wilt… and crumble into dust in seconds.", 35);
        pause(1200);

        slowPrint("\nThe deeper you walk, the louder the forest feels.", 35);
        slowPrint("Groans echo through the bark. Roots shift beneath your feet.", 35);
        pause(1200);

        slowPrint("\nSomething is wrong here.", 35);
        slowPrint("Not a natural wrong — a corruption, spreading like a sickness.", 35);
        pause(1200);

        slowPrint("\nWhispers roll between the branches.", 35);
        slowPrint("Faint… haunting… almost like words:", 35);
        pause(900);

        slowPrint("\n   \"The Ember Star… it wakes…\"", 45);
        pause(1200);

        slowPrint("\nYour pulse quickens.", 35);
        slowPrint("If this corruption continues to grow… the forest will tear itself apart.", 35);
        pause(1200);

        slowPrint("\nThis is Sorrowglade.", 35);
        slowPrint("Once peaceful. Now twisting under the Star’s influence… and calling out for help.", 35);
        pause(1200);

        System.out.println();
        waitForEnter();
    }

    public static void playCassianIntro(Player player) {

        // Dev choice to skip/play
        if (devSkipCheck(player, "Cassian Intro")) return;

        // If played once
        if (NPCManager.cassianCutscenePlayed) {
            slowPrint("\nCassian: \"The Manor hasn’t quieted yet… keep your guard up.\"", 35);
            waitForEnter();
            return;
        }
        NPCManager.cassianCutscenePlayed = true;

        System.out.println("\n--- CUTSCENE: Cassian 'Ash' Vale ---\n");
        pause(800);

        slowPrint("Ghost Town is silent… too silent.", 35);
        pause(700);
        slowPrint("Dust drifts across empty roads. Lanterns flicker without wind.", 35);
        pause(900);

        slowPrint("\nA ghostly figure leans against a broken post.", 35);
        slowPrint("Half-present, half-memory. His outline shimmers like fading smoke.", 35);
        pause(1200);

        slowPrint("\nCassian 'Ash' Vale:", 35);
        slowPrint("\"Took you long enough to notice me…\"", 40);
        pause(900);

        slowPrint("\nHis golden eyes glow faintly, burning with a memory he can’t name.", 35);
        slowPrint("\"Relax. I ain’t here to shoot you. Not unless I forget who I am again.\"", 35);
        pause(1100);

        slowPrint("\nThe wood beneath him creaks — but he casts no weight, no shadow.", 35);
        pause(1000);

        slowPrint("Cassian:", 35);
        slowPrint("\"This town… it ain’t dead. It’s stuck. Like me.\"", 40);
        pause(1000);

        slowPrint("\nHe taps the side of his head, as if chasing a memory.", 35);
        slowPrint("\"Don’t know how I died. Don’t know why I’m still here.\"", 35);
        pause(900);

        slowPrint("\nSuddenly, his voice splits — two tones overlapping.", 35);
        slowPrint("A soft whisper buried beneath a deeper, broken snarl.", 40);
        pause(900);

        slowPrint("\nCassian (distorted):", 35);
        slowPrint("\"The Manor calls… the walls breathe… the door bleeds…\"", 45);
        pause(1300);

        slowPrint("\nHe blinks rapidly, steadying himself as the possession fades.", 35);
        pause(800);

        slowPrint("Cassian:", 35);
        slowPrint("\"Ignore that. Happens sometimes.\"", 35);
        pause(900);

        slowPrint("\n\"Listen. The Manor up ahead?\"", 35);
        slowPrint("\"It’s waking up. Rooms shifting. Voices crawling out of the walls.\"", 35);
        pause(1200);

        slowPrint("\"Whatever's inside… it remembers me. And it doesn’t like me.\"", 35);
        pause(1100);

        slowPrint("\nHe steps forward — or flickers closer.", 35);
        slowPrint("\"You’re alive. That makes you useful.\"", 35);
        pause(1000);

        slowPrint("\n\"Will you explore the Manor?\"", 35);
        slowPrint("\"Find the source of whatever’s calling… before it finds us first.\"", 35);
        pause(1200);

        // ============================
        // CHOICE: Accept or Decline
        // ============================
        System.out.print("\nAccept quest 'Echoes in the Manor'? [Y/N]: ");
        String ans = RPGGame.sc.nextLine().trim().toUpperCase();

        while (!ans.equals("Y") && !ans.equals("N")) {
            System.out.print("Invalid. Enter Y or N: ");
            ans = RPGGame.sc.nextLine().trim().toUpperCase();
        }

        if (ans.equals("Y")) {
            player.cassianQuestStarted = true;

            slowPrint("\n--- QUEST ACCEPTED ---", 0);
            slowPrint("Explore the haunted Manor at the edge of Ghost Town.", 0);

            pause(800);
            slowPrint("\nCassian: \"Good. Then keep your gun—\"", 35);
            pause(450);
            slowPrint("he pauses, realizing you don’t have one.", 35);
            pause(600);
            slowPrint("\"—uh… keep whatever you fight with close. You’ll need it.\"", 35);

        } else {
            slowPrint("\nCassian: \"Heh. Can't blame you.\"", 35);
            slowPrint("\"The Manor doesn’t welcome the living anyway.\"", 35);
            pause(800);
            slowPrint("\"Still… if you change your mind, I’ll be right here.\"", 35);
        }

        waitForEnter();
    }


    // ============================
    // GHOST TOWN INTRO
    // ============================

    public static void playGhostTownIntro(Player player) {

        if (NPCManager.ghostTownIntroPlayed) return;
        NPCManager.ghostTownIntroPlayed = true;

        System.out.println("\n--- CUTSCENE: Entering Ghost Town ---\n");

        slowPrint("A dry wind sweeps across the abandoned street…", 35);
        pause(900);

        slowPrint("Shutters creak. Dust swirls. Not a single footprint in sight.", 35);
        pause(1100);

        slowPrint("\nThe buildings look frozen in time —", 35);
        slowPrint("doors half-open as if their owners left in a hurry.", 35);
        pause(1200);

        slowPrint("\nA sign dangles from rusty chains above:", 35);
        slowPrint("  'SILVERWELL — POPULATION: 214' …but the paint is scratched out.", 40);
        pause(1300);

        slowPrint("\nYour footsteps echo unnaturally loud…", 35);
        slowPrint("as if the town itself is listening.", 35);
        pause(1100);

        slowPrint("\nWhispers ride the desert wind:", 45);
        slowPrint("  \"Turn back…\"", 45);
        slowPrint("  \"He took them…\"", 45);
        pause(1400);

        slowPrint("\nA lantern flickers inside a window — but no one is there.", 35);
        pause(1200);

        slowPrint("\nYou feel it in your chest:", 35);
        slowPrint("This place isn’t abandoned.", 35);
        slowPrint("It’s *waiting*.", 35);
        pause(1500);

        slowPrint("\nThe air tightens…", 35);
        slowPrint("as if a hundred unseen eyes watch your every move.", 40);
        pause(1300);

        slowPrint("\nThis is Ghost Town.", 35);
        slowPrint("Once a frontier outpost — now a grave of secrets.", 35);
        pause(1000);

        slowPrint("\nSomewhere in this silence… lies your next clue to Elias.", 35);

        waitForEnter();
    }


    // ============================
    // BOSS CUTSCENE
    // ============================
    public static void playBossCutscene(Player player, Enemies.Enemy boss) {
        if (player.isDev()) {
            System.out.println("[Cutscene skipped - Dev Mode]");
            return;
        }
        System.out.println("\n!!! BOSS CUTSCENE !!!");
        pause(1000);
        System.out.println("The ground shakes violently...");
        pause(1000);
        System.out.println("A massive shadow looms...");
        pause(1000);
        System.out.println(boss.name + " rises from the rubble!");
        pause(1000);

        waitForEnter(); // BLOCK GAMEPLAY
    }


    public static void playCrystalChamberUnlockedCutscene() {

        System.out.println("\n--- CUTSCENE: Crystal Chamber Unsealed ---\n");

        slowPrint("The Crystal Heart cracks...", 35);
        pause(900);

        slowPrint("A surge of radiant light bursts outward.", 35);
        pause(900);

        slowPrint("Somewhere deep within the forest...", 35);
        slowPrint("A crystal barrier shatters.", 35);
        pause(900);

        slowPrint("\n\"The path to the Crystal Chambers… is now open.\"", 45);
        waitForEnter();
    }

    public static void playCrystalChamberIntro(Player player) {

        // Prevent replay
        if (NPCManager.crystalChamberIntroPlayed) return;
        NPCManager.crystalChamberIntroPlayed = true;

        System.out.println("\n--- CUTSCENE: Crystal Chamber ---\n");

        slowPrint("The air grows unnaturally cold as you descend…", 35);
        pause(900);
        slowPrint("A faint humming echoes through the stone walls.", 35);
        pause(900);

        slowPrint("\nLight spills forward — not warm, but sharp, crystalline.", 35);
        slowPrint("The chamber opens before you… a cathedral of shimmering stone.", 35);
        pause(1200);

        slowPrint("\nMassive crystal pillars rise toward the ceiling,", 35);
        slowPrint("their surfaces pulsing with a heartbeat-like glow.", 35);
        pause(1000);

        slowPrint("\nEvery step you take vibrates through the floor.", 35);
        slowPrint("Whispers drift around you — distant, disjointed, ancient.", 45);
        pause(1200);

        slowPrint("\n   \"The Heart watches…\"", 45);
        pause(1200);

        slowPrint("\nA gust of icy wind rushes past.", 35);
        pause(700);

        slowPrint("Something powerful… something alive… stirs within the chamber.", 35);
        pause(1200);

        slowPrint("\nYou sense it watching you back.", 35);

        waitForEnter();
    }


    public static void playCyrillFreedCutscene(Player player) {

        if (NPCManager.cyrillCutscenePlayed) return;
        NPCManager.cyrillCutscenePlayed = true;

        System.out.println("\n--- CUTSCENE: Cyrill’s Awakening ---\n");

        slowPrint("The chamber falls silent…", 35);
        pause(800);

        slowPrint("Where Cyrill once stood blazing with corrupted fury,", 35);
        slowPrint("she now collapses to her knees, clutching her head.", 35);
        pause(1100);

        slowPrint("\nCyrill: \"…W-What… have I… done?\"", 40);
        pause(900);

        slowPrint("The red glow fades from her eyes as clarity returns.", 35);
        pause(900);

        slowPrint("\nCyrill: \"The corruption… the Ember Star…\"", 35);
        slowPrint(" \"It twisted my thoughts… drowned out my own voice…\"", 35);
        pause(1200);

        slowPrint("\nShe struggles to rise, leaning against a crystal pillar.", 35);
        pause(900);

        slowPrint("Cyrill: \"You… you freed me.\"", 40);
        pause(800);

        slowPrint("\nHer gaze softens — no longer a threat, but wounded.", 35);
        pause(900);

        slowPrint("Cyrill: \"Elias… he passed through here after taking the Star.\"", 35);
        slowPrint(" \"His mind was already breaking. The Heart tried to resist him… but failed.\"", 35);
        pause(1300);

        slowPrint("\nCyrill: \"He headed deeper into Dustveil… toward the old Prison.\"",
                35);
        pause(1100);

        slowPrint("\nCyrill bows her head, ashamed.", 35);
        slowPrint("\"If you cross paths with him again… please stop him.\"",
                45);
        pause(1300);

        slowPrint("\nThe crystals around you dim, as if exhaling after centuries of tension.", 35);
        pause(900);

        slowPrint("Cyrill: \"Go. Dustveil is changing… and not for the better.\"", 35);

        waitForEnter();
        NPCManager.tessaraMovedToCavern = true;
    }

    public static void playFurnaceBossIntro(Player player, Enemies.Enemy boss) {

        if (devSkipCheck(player, "Furnace Boss Intro")) return;

        System.out.println("\n--- CUTSCENE: The Ashen Blight Stirs ---\n");
        pause(800);

        slowPrint("Heat presses against your skin… far hotter than any forge.", 35);
        pause(900);

        slowPrint("The walls glow red. Embers fall like dying snow.", 35);
        pause(900);

        slowPrint("\nA heartbeat echoes through the chamber…", 35);
        slowPrint("…but it isn't yours.", 35);
        pause(1200);

        slowPrint("\nFrom the molten pit, a massive figure rises.", 35);
        slowPrint("Charred bone. Liquid fire. Eyes like furnace vents.", 35);
        pause(1200);

        slowPrint("\nIgnivar: \"I was forged in suffering… and you will return to ash.\"", 45);
        pause(1400);

        waitForEnter();
    }

    public static void playFurnaceBossDefeated() {
        System.out.println("\nThe flames recede… the chamber cools.");
        pause(800);
        slowPrint("The Ashen Blight collapses, its core dimming.", 35);
        pause(900);
        slowPrint("\nA path deeper into the manor creaks open…", 35);
        waitForEnter();
    }



    // ============================
    // CAVERNS UNLOCKED CUTSCENE
    // ============================
    public static void playCavernsUnlockedCutscene() {
        System.out.println("\n!!! CUTSCENE !!!");
        pause(1000);
        System.out.println("A hidden passage opens...");
        pause(1000);
        System.out.println("You can now enter The Caverns.");
        pause(1000);

        waitForEnter(); // BLOCK GAMEPLAY
    }

    // ============================
    // DEV CUTSCENE
    // ============================

    private static boolean devSkipCheck(Player player, String sceneName) {
        if (!player.isDev()) return false;

        System.out.println("\n=== DEV CONTROL: Cutscene Override ===");
        System.out.println("Scene: " + sceneName);
        System.out.println("[1] Skip Scene");
        System.out.println("[2] Play Scene");
        System.out.print("Choice: ");

        String in = RPGGame.sc.nextLine().trim();

        if (in.equals("1")) {
            System.out.println("[DEV] Scene skipped.\n");
            return true;
        }
        return false;
    }

}
