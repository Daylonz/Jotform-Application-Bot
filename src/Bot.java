import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.DisconnectEvent;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {
    private static final String BOT_TOKEN = "BOT_TOKEN";
    private static Logger log;
    private boolean started = false;

    public static void main(String args[]) throws LoginException
    {
        log = LoggerFactory.getLogger(Bot.class);
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(BOT_TOKEN)
                .addEventListener(new Bot())
                .build();
    }

    @Override
    public void onReady(ReadyEvent event)
    {
        if (!started) {
            Thread thread = new Thread(new ApplicationChecker(event));
            thread.start();
            started = true;
        }
        log.info("Appchecker Started.");
    }

    @Override
    public void onDisconnect(DisconnectEvent event) {
        log.info("Bot disconnected. Code: " + event.getCloseCode());
    }

    @Override
    public void onReconnect(ReconnectedEvent event) {
        log.info("Bot successfully reconnected.");
    }
}