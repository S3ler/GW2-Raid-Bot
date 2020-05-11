package me.cbitler.raidbot.utility;

import me.cbitler.raidbot.RaidBot;
import net.dv8tion.jda.core.entities.Emote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reactions {
    /**
     * List of reactions representing classes
     */
    static String[] specs = {
            "Dragonhunter", // 530541439180996638
            "Firebrand", // 530541440300744705
            "Herald", // 530541440086966283
            "Renegade", // 530541439478661120
            "Berserker", // 530541439625592852
            "Spellbreaker", // 530541439675793408
            "Scrapper", // 530541438895652914
            "Holosmith", // 530541438837194753
            "Druid", // 530541439105499136
            "Soulbeast", // 530541439415746585
            "Daredevil", // 530541439705415680
            "Deadeye", // 530541440225509377
            "Weaver", // 530541439239716874
            "Tempest", // 530541439252430858
            "Chronomancer", // 530541439621267456
            "Mirage", // 530541438707040277
            "Reaper", // 530541439038259216
            "Scourge", // 530541439772393484
            "Guardian", // 530541438891589643
            "Revenant", // 530541439751290901
            "Warrior", // 530541440145555466
            "Engineer", // 530541439172608062
            "Ranger", // 530541439642501121
            "Thief", // 530541439701221378
            "Elementalist", // 530541438891589642
            "Mesmer", // 530541438769823747
            "Necromancer" // 530541439218876416
    };
    
    public static String[] coreClasses = {
            "Guardian", // 530541438891589643
            "Revenant", // 530541439751290901
            "Warrior", // 530541440145555466
            "Engineer", // 530541439172608062
            "Ranger", // 530541439642501121
            "Thief", // 530541439701221378
            "Elementalist", // 530541438891589642
            "Mesmer", // 530541438769823747
            "Necromancer" // 530541439218876416
    };

    static Emote[] reactions = {
            getEmoji("671026775584669726"), // Dragonhunter
            getEmoji("671026777619169325"), // Firebrand
            getEmoji("671026777547866124"), // Herald
            getEmoji("671026778067959848"), // Renegade
            getEmoji("671026775362633750"), // Berserker
            getEmoji("671026777782616090"), // Spellbreaker
            getEmoji("671026778479001627"), // Scrapper
            getEmoji("671026778185138195"), // Holosmith
            getEmoji("671026775643521034"), // Druid
            getEmoji("671026780479684628"), // Soulbeast
            getEmoji("671026775370891304"), // Daredevil
            getEmoji("671026775538663474"), // Deadeye
            getEmoji("671026778080411650"), // Weaver
            getEmoji("671026778072023074"), // Tempest
            getEmoji("671026775551115295"), // Chronomancer
            getEmoji("671026777765707776"), // Mirage
            getEmoji("671026777879085057"), // Reaper
            getEmoji("671026778726334515"), // Scourge
            getEmoji("671026779112210471"), // Guardian
            getEmoji("671026779091107852"), // Revenant
            getEmoji("671026779451949057"), // Warrior
            getEmoji("671026777371443200"), // Engineer
            getEmoji("671026779665989673"), // Ranger
            getEmoji("671026778818478109"), // Thief
            getEmoji("671026777388482560"), // Elementalist
            getEmoji("671026779078524950"), // Mesmer
            getEmoji("671026779426914335"), // Necromancer
            getEmoji("671026778378338309"), // Flex
            getEmoji("671026779451818002"), // Swap
            getEmoji("671026778885849100") // X_
    };

    static Emote[] reactionsCore = {
            getEmoji("671026779112210471"), // Guardian
            getEmoji("671026779091107852"), // Revenant
            getEmoji("671026779451949057"), // Warrior
            getEmoji("671026777371443200"), // Engineer
            getEmoji("671026779665989673"), // Ranger
            getEmoji("671026778818478109"), // Thief
            getEmoji("671026777388482560"), // Elementalist
            getEmoji("671026779078524950"), // Mesmer
            getEmoji("671026779426914335"), // Necromancer
            getEmoji("671026778378338309"), // Flex
            getEmoji("671026779451818002"), // Swap
            getEmoji("671026778885849100") // X_
    };

    static Emote[] reactionsOpenWorld = {
            getEmoji("671026775593058304"), // Check
            getEmoji("671026778885849100")  // X_
    };

    /**
     * Get an emoji from it's emote ID via JDA
     *
     * @param id The ID of the emoji
     * @return The emote object representing that emoji
     */
    private static Emote getEmoji(String id) {
        return RaidBot.getInstance().getJda().getEmoteById(id);
    }

    /**
     * Get the list of reaction names as a list
     *
     * @return The list of reactions as a list
     */
    public static List<String> getSpecs() {
        return new ArrayList<>(Arrays.asList(specs));
    }

    /**
     * Get the list of emote objects
     *
     * @return The emotes
     */
    public static List<Emote> getEmotes() {
        return new ArrayList<>(Arrays.asList(reactions));
    }

    /**
     * Get the list of core class emote objects
     *
     * @return The emotes
     */
    public static List<Emote> getCoreClassEmotes() {
        return new ArrayList<>(Arrays.asList(reactionsCore));
    }
    
    /**
     * Get the list of open world emote objects
     *
     * @return The emotes
     */
    public static List<Emote> getOpenWorldEmotes() {
        return new ArrayList<>(Arrays.asList(reactionsOpenWorld));
    }

    public static Emote getEmoteByName(String name) {
        for (Emote emote : reactions) {
            if (emote != null && emote.getName().equalsIgnoreCase(name)) {
                return emote;
            }
        }
        return null;
    }
}
