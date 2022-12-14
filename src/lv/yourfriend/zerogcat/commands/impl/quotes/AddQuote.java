package lv.yourfriend.zerogcat.commands.impl.quotes;

import java.util.ArrayList;

import lv.yourfriend.zerogcat.commands.Command;
import lv.yourfriend.zerogcat.utils.Config;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import java.nio.ByteBuffer;

import org.lmdbjava.CursorIterable.KeyVal;

public class AddQuote extends Command {
    public AddQuote() {
        super("addquote", "Add a quote", "addquote \"im the funniest ever\" - yourfriend", Category.QUOTES);
    }

    public void execute(ArrayList<String> args, Member author, Guild guild, Message message) {
        if (args.size() == 0) {
            message.reply("being in 0 gravity won't stop you from including your arguments").queue();
            return;
        }

        int createdQuotes = 0;
        for (final KeyVal<ByteBuffer> kv : Config.db.Entries()) {
            String[] parts = Config.db.bts(kv.key()).split("-");
            if (parts[0].equals("quote") && parts[1].equals(author.getId()))
                createdQuotes++;
        }
        Config.db.Clean();

        if (createdQuotes >= Config.maxAmountOfQuotes) {
            message.reply("You have the max amount of quotes added!").queue();
            return;
        }
        String quote = String.join(" ", args);

        if (quote.length() >= 50) {
            message.reply("A quote cannot be bigger than 50 characters.").queue();
            return;
        }

        String id = String.valueOf((int) (Math.random() * 1000000));
        Config.db.Set("quote-" + author.getId() + "-" + id, quote);
        message.reply("Quote added! Id " + id + ". " + (createdQuotes + 1) + " out of " + Config.maxAmountOfChannels
                + " quotes.").queue();

        return;
    }
}
