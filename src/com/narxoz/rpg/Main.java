package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");


        // 1. Heroes
        Hero lelouch = new Hero("LELOUCH",120,25, 15);
        Hero cc = new Hero("CC",90,30,8);
        System.out.println("Party: " + lelouch.getName() + ", " + cc.getName());

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Goblin Den",QuestPriority.LOW,100,false));
        questLog.add(new Quest("Dragon Lair",QuestPriority.URGENT,500, true));
        questLog.add(new Quest("Cursed Ruins",QuestPriority.HIGH, 300, false));
        questLog.add(new Quest("Escort Caravan",QuestPriority.NORMAL, 150, false));
        questLog.add(new Quest("Ancient Temple", QuestPriority.HIGH,450,true));
        System.out.println("QuestLog loaded with " + questLog.size() + " quests");

        GuildHall hall = new GuildHall();
        Captain captain = new Captain("Askelad",hall);
        Quartermaster quartermaster = new Quartermaster("Targarien",hall);
        Scout scout = new Scout("JonSnow", hall);
        Healer healer = new Healer("Gandalf",hall);
        Loremaster loremaster = new Loremaster("Thorfin",hall);

        hall.register(captain);
        hall.register(quartermaster);
        hall.register(scout);
        hall.register(healer);
        hall.register(loremaster);

        System.out.println("Guild council assembled");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(List.of(lelouch, cc), questLog, hall);

        System.out.println(result);
        System.out.println("War Council Completed Successfully");
    }
}
