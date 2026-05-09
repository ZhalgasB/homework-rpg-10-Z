package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import java.util.List;

public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;
        int messagesRouted = 0;

        System.out.println(" Starting War Planning ");

        QuestIterator[] iterators = {
                questLog.ordered(),
                questLog.reverse(),
                questLog.priorityAtLeast(com.narxoz.rpg.quest.QuestPriority.HIGH),
                questLog.rewardSorted()
        };

        for (QuestIterator it : iterators) {
            while (it.hasNext()) {
                var quest = it.next();
                questsTraversed++;
                System.out.println("quest: " + quest);


                if (hall instanceof com.narxoz.rpg.guild.GuildHall gh) {
                    gh.dispatch("scouting",null, "Scout " + quest.getTitle());
                    gh.dispatch("supplies",null, "Prepare gear for " + quest.getTitle());
                    gh.dispatch("healing",null, "Medical support needed");
                    gh.dispatch("orders",null, "Engage " + quest.getTitle());
                }
            }
        }

        if (hall instanceof com.narxoz.rpg.guild.GuildHall gh) {
            messagesRouted = gh.getMessagesRouted();
            int notified = gh.getMembersNotified();
            return new CouncilRunResult(questsTraversed,messagesRouted,notified);
        }

        return new CouncilRunResult(questsTraversed,messagesRouted, 0);
    }
}
