package lv.yourfriend.zerogcat.utils;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Channels {
    public static Integer getMaxChannels(Long id) {
        String nonint = Config.db.data.get("limits-" + id);

        if (nonint == null)
            return Config.maxAmountOfChannels;
        else
            return Integer.valueOf(nonint);
    }

    public static ArrayList<TextChannel> channelsOwnedByID(Guild guild, String id) {
        ArrayList<TextChannel> channelsOwned = new ArrayList<>();

        for (TextChannel z : guild.getCategoryById(Config.db.data.get("category-" + guild.getIdLong()))
                .getTextChannels()) {
            if (z.getTopic().equals(id)) {
                channelsOwned.add(z);
            }
        }

        return channelsOwned;
    }
}
