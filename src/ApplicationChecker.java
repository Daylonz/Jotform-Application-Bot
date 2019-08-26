import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class ApplicationChecker implements Runnable {
    private static final String SERVER_ID = "129078539118051329";
    private static final String RL_CHANNEL_ID = "509168929743306752";
    private static final String PUBG_CHANNEL_ID = "509169132017549312";
    private static final String FORTNITE_CHANNEL_ID = "509169151986630678";
    private static final String APEX_CHANNEL_ID = "543868449760215050";
    private static final String GC_ROLE_ID = "394338640962125824";
    private static final String C3_ROLE_ID = "394338504445919233";
    private static final String C2_ROLE_ID = "442824854278766603";
    private static final String C1_ROLE_ID = "399744333009518622";
    private static final String PUBG_ROLE_ID = "428373986448113684";
    private static final String FORTNITE_ROLE_ID = "406125056251658240";
    private static final String APEX_ROLE_ID = "543825901008453632";
    private Date last = new Date();
    private Event event;

    ApplicationChecker(Event event) {
        this.event = event;
    }

    public void run() {
        System.out.println("STARTING APP CHECKER EVENT!");
        try {
            while (true) {
                try {
                    JotformParser.checkRLApplicants(last);
                    JotformParser.checkPUBGApplicants(last);
                    JotformParser.checkFortniteApplicants(last);
                    JotformParser.checkApexApplicants(last);

                    RLApplicantCheck(event);
                    PUBGApplicantCheck(event);
                    FortniteApplicantCheck(event);
                    ApexApplicantCheck(event);
                    last = new Date();
                    Thread.sleep(30000);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void RLApplicantCheck(Event event) {
        EmbedBuilder gcapp, c3app, c2app, c1app;
        TextChannel textChannel = event.getJDA().getTextChannelById(RL_CHANNEL_ID);
        if (textChannel != null) {
            gcapp = getGCApplicants(event);
            if (gcapp != null)
                textChannel.sendMessage(gcapp.build()).queue();

            c3app = getC3Applicants(event);
            if (c3app != null)
                textChannel.sendMessage(c3app.build()).queue();

            c2app = getC2Applicants(event);
            if (c2app != null)
                textChannel.sendMessage(c2app.build()).queue();

            c1app = getC1Applicants(event);
            if (c1app != null)
                textChannel.sendMessage(c1app.build()).queue();
        }
    }

    private void PUBGApplicantCheck(Event event) {
        EmbedBuilder applicant;
        TextChannel textChannel = event.getJDA().getTextChannelById(PUBG_CHANNEL_ID);
        if (textChannel != null) {
            applicant = getPUBGApplicants(event);
            if (applicant != null)
                textChannel.sendMessage(applicant.build()).queue();
        }
    }

    private void FortniteApplicantCheck(Event event) {
        EmbedBuilder applicant;
        TextChannel textChannel = event.getJDA().getTextChannelById(FORTNITE_CHANNEL_ID);
        if (textChannel != null) {
            applicant = getFortniteApplicants(event);
            if (applicant != null)
                textChannel.sendMessage(applicant.build()).queue();
        }
    }

    private void ApexApplicantCheck(Event event) {
        EmbedBuilder applicant;
        TextChannel textChannel = event.getJDA().getTextChannelById(APEX_CHANNEL_ID);
        if (textChannel != null) {
            applicant = getApexApplicants(event);
            if (applicant != null)
                textChannel.sendMessage(applicant.build()).queue();
        }
    }

    private boolean addRole(Event event, String roleId, String name) {
        List<User> users = event.getJDA().getUsersByName(name.split("#", 2)[0], false);
        if (users.size() > 0)
        {
            User user = users.get(0);
            Member member = event.getJDA().getGuildById(SERVER_ID).getMember(user);
            if (member.getRoles().stream().anyMatch(e -> e.getName().contains("Sway") || e.getName().contains("Applicant")))
            {
                System.out.println("Role not given to member " + member.getNickname() + " due to filter.");
                return false;
            }
            Role role = event.getJDA().getGuildById(SERVER_ID).getRoleById(roleId);
            if (role == null)
            {
                System.out.println("Member or role was null. Could not give add role to applicant.");
                return false;
            }
            event.getJDA().getGuildById(SERVER_ID).getController().addSingleRoleToMember(member,role).queue();
            return true;
        }
        else {
            System.out.println("Could not find user " + name.split("#", 2)[0]);
            return false;
        }
    }

    private EmbedBuilder getGCApplicants(Event event)
    {
        List<RLApplicant> gcapps = JotformParser.getGCapplicants();
        if (gcapps.size() > 0)
        {
            EmbedBuilder result = new EmbedBuilder();
            result.setColor(Color.green);
            result.setThumbnail("https://i.imgur.com/nuX0o9z.png");
            result.setTitle("New Grand Champion Applicant");
            result.addField("Discord Name", gcapps.get(0).getDiscordName(), true);
            result.addField("Steam Name", gcapps.get(0).getSteamName(), true);
            result.addField("Steam Link", gcapps.get(0).getSteamLink(), true);
            result.addField("Age", gcapps.get(0).getAge(), true);
            result.addField("Highest Rank", gcapps.get(0).getHighestRank(), true);
            result.addField("Hours Played", gcapps.get(0).getHoursPlayed(), true);
            if (gcapps.get(0).getMMR() != null)
                result.addField("MMR", gcapps.get(0).getMMR(), true);
            if (gcapps.get(0).getMMR() != null)
                result.addField("MMR", gcapps.get(0).getMMR(), true);
            if (gcapps.get(0).getOrigin() != null)
                result.addField("Origin", gcapps.get(0).getOrigin(), true);
            if (gcapps.get(0).getMMR() != null)
                result.addField("MMR", gcapps.get(0).getMMR(), true);
            if (gcapps.get(0).getServer() != null)
                result.addField("Server Preference", gcapps.get(0).getServer(), true);
            if (gcapps.get(0).getPlaystyle() != null)
                result.addField("Playstyle", gcapps.get(0).getPlaystyle(), true);
            if (gcapps.get(0).getExperience() != null)
                result.addField("Previous Experience", gcapps.get(0).getExperience(), true);
            if (gcapps.get(0).getStrongestAsset() != null)
                result.addField("Strongest Asset(s)", gcapps.get(0).getStrongestAsset(), true);
            if (gcapps.get(0).getNeedsImprovement() != null)
                result.addField("Needs Improvement", gcapps.get(0).getNeedsImprovement(), true);
            if (gcapps.get(0).getGoals() != null)
                result.addField("Goals With Sway", gcapps.get(0).getGoals(), true);
            if (gcapps.get(0).getAnythingElse() != null)
                result.addField("Other Comments", gcapps.get(0).getAnythingElse(), true);
                result.addField("Role Given", addRole(event, GC_ROLE_ID, gcapps.get(0).getDiscordName()) ? "Yes" : "No", true);
            return result;
        }
        return null;
    }

    private EmbedBuilder getC3Applicants(Event event)
    {
        List<RLApplicant> c3apps = JotformParser.getC3applicants();
        if (c3apps.size() > 0)
        {
            EmbedBuilder result = new EmbedBuilder();
            result.setColor(Color.green);
            result.setThumbnail("https://rocketleague.tracker.network/Images/RL/ranked/s4-18.png");
            result.setTitle("New Champion 3 Applicant");
            result.addField("Discord Name", c3apps.get(0).getDiscordName(), true);
            result.addField("Steam Name", c3apps.get(0).getSteamName(), true);
            result.addField("Steam Link", c3apps.get(0).getSteamLink(), true);
            result.addField("Age", c3apps.get(0).getAge(), true);
            result.addField("Highest Rank", c3apps.get(0).getHighestRank(), true);
            result.addField("Hours Played", c3apps.get(0).getHoursPlayed(), true);
            if (c3apps.get(0).getMMR() != null)
                result.addField("MMR", c3apps.get(0).getMMR(), true);
            if (c3apps.get(0).getMMR() != null)
                result.addField("MMR", c3apps.get(0).getMMR(), true);
            if (c3apps.get(0).getOrigin() != null)
                result.addField("Origin", c3apps.get(0).getOrigin(), true);
            if (c3apps.get(0).getMMR() != null)
                result.addField("MMR", c3apps.get(0).getMMR(), true);
            if (c3apps.get(0).getServer() != null)
                result.addField("Server Preference", c3apps.get(0).getServer(), true);
            if (c3apps.get(0).getPlaystyle() != null)
                result.addField("Playstyle", c3apps.get(0).getPlaystyle(), true);
            if (c3apps.get(0).getExperience() != null)
                result.addField("Previous Experience", c3apps.get(0).getExperience(), true);
            if (c3apps.get(0).getStrongestAsset() != null)
                result.addField("Strongest Asset(s)", c3apps.get(0).getStrongestAsset(), true);
            if (c3apps.get(0).getNeedsImprovement() != null)
                result.addField("Needs Improvement", c3apps.get(0).getNeedsImprovement(), true);
            if (c3apps.get(0).getGoals() != null)
                result.addField("Goals With Sway", c3apps.get(0).getGoals(), true);
            if (c3apps.get(0).getAnythingElse() != null)
                result.addField("Other Comments", c3apps.get(0).getAnythingElse(), true);
            result.addField("Role Given", addRole(event, C3_ROLE_ID, c3apps.get(0).getDiscordName()) ? "Yes" : "No", true);
            return result;
        }
        return null;
    }

    private EmbedBuilder getC2Applicants(Event event)
    {
        List<RLApplicant> c2apps = JotformParser.getC2applicants();
        if (c2apps.size() > 0)
        {
            addRole(event, C2_ROLE_ID, c2apps.get(0).getDiscordName());
            EmbedBuilder result = new EmbedBuilder();
            result.setColor(Color.green);
            result.setThumbnail("https://rocketleague.tracker.network/Images/RL/ranked/s4-17.png");
            result.setTitle("New Champion 2 Applicant");
            result.addField("Discord Name", c2apps.get(0).getDiscordName(), true);
            result.addField("Steam Name", c2apps.get(0).getSteamName(), true);
            result.addField("Steam Link", c2apps.get(0).getSteamLink(), true);
            result.addField("Age", c2apps.get(0).getAge(), true);
            result.addField("Highest Rank", c2apps.get(0).getHighestRank(), true);
            result.addField("Hours Played", c2apps.get(0).getHoursPlayed(), true);
            if (c2apps.get(0).getMMR() != null)
                result.addField("MMR", c2apps.get(0).getMMR(), true);
            if (c2apps.get(0).getMMR() != null)
                result.addField("MMR", c2apps.get(0).getMMR(), true);
            if (c2apps.get(0).getOrigin() != null)
                result.addField("Origin", c2apps.get(0).getOrigin(), true);
            if (c2apps.get(0).getMMR() != null)
                result.addField("MMR", c2apps.get(0).getMMR(), true);
            if (c2apps.get(0).getServer() != null)
                result.addField("Server Preference", c2apps.get(0).getServer(), true);
            if (c2apps.get(0).getPlaystyle() != null)
                result.addField("Playstyle", c2apps.get(0).getPlaystyle(), true);
            if (c2apps.get(0).getExperience() != null)
                result.addField("Previous Experience", c2apps.get(0).getExperience(), true);
            if (c2apps.get(0).getStrongestAsset() != null)
                result.addField("Strongest Asset(s)", c2apps.get(0).getStrongestAsset(), true);
            if (c2apps.get(0).getNeedsImprovement() != null)
                result.addField("Needs Improvement", c2apps.get(0).getNeedsImprovement(), true);
            if (c2apps.get(0).getGoals() != null)
                result.addField("Goals With Sway", c2apps.get(0).getGoals(), true);
            if (c2apps.get(0).getAnythingElse() != null)
                result.addField("Other Comments", c2apps.get(0).getAnythingElse(), true);
            result.addField("Role Given", addRole(event, C2_ROLE_ID, c2apps.get(0).getDiscordName()) ? "Yes" : "No", true);
            return result;
        }
        return null;
    }

    private EmbedBuilder getC1Applicants(Event event)
    {
        List<RLApplicant> c1apps = JotformParser.getC1applicants();
        if (c1apps.size() > 0)
        {
            addRole(event, C1_ROLE_ID, c1apps.get(0).getDiscordName());
            EmbedBuilder result = new EmbedBuilder();
            result.setColor(Color.green);
            result.setThumbnail("https://rocketleague.tracker.network/Images/RL/ranked/s4-16.png");
            result.setTitle("New Champion 1 Applicant");
            result.addField("Discord Name", c1apps.get(0).getDiscordName(), true);
            result.addField("Steam Name", c1apps.get(0).getSteamName(), true);
            result.addField("Steam Link", c1apps.get(0).getSteamLink(), true);
            result.addField("Age", c1apps.get(0).getAge(), true);
            result.addField("Highest Rank", c1apps.get(0).getHighestRank(), true);
            result.addField("Hours Played", c1apps.get(0).getHoursPlayed(), true);
            if (c1apps.get(0).getMMR() != null)
                result.addField("MMR", c1apps.get(0).getMMR(), true);
            if (c1apps.get(0).getMMR() != null)
                result.addField("MMR", c1apps.get(0).getMMR(), true);
            if (c1apps.get(0).getOrigin() != null)
                result.addField("Origin", c1apps.get(0).getOrigin(), true);
            if (c1apps.get(0).getMMR() != null)
                result.addField("MMR", c1apps.get(0).getMMR(), true);
            if (c1apps.get(0).getServer() != null)
                result.addField("Server Preference", c1apps.get(0).getServer(), true);
            if (c1apps.get(0).getPlaystyle() != null)
                result.addField("Playstyle", c1apps.get(0).getPlaystyle(), true);
            if (c1apps.get(0).getExperience() != null)
                result.addField("Previous Experience", c1apps.get(0).getExperience(), true);
            if (c1apps.get(0).getStrongestAsset() != null)
                result.addField("Strongest Asset(s)", c1apps.get(0).getStrongestAsset(), true);
            if (c1apps.get(0).getNeedsImprovement() != null)
                result.addField("Needs Improvement", c1apps.get(0).getNeedsImprovement(), true);
            if (c1apps.get(0).getGoals() != null)
                result.addField("Goals With Sway", c1apps.get(0).getGoals(), true);
            if (c1apps.get(0).getAnythingElse() != null)
                result.addField("Other Comments", c1apps.get(0).getAnythingElse(), true);
            result.addField("Role Given", addRole(event, C1_ROLE_ID, c1apps.get(0).getDiscordName()) ? "Yes" : "No", true);
            return result;
        }
        return null;
    }

    private EmbedBuilder getPUBGApplicants(Event event)
    {
        List<PUBGApplicant> apps = JotformParser.getPUBGapplicants();
        if (apps.size() > 0)
        {
            addRole(event, PUBG_ROLE_ID, apps.get(0).getDiscordName());
            EmbedBuilder result = new EmbedBuilder();
            result.setColor(Color.green);
            result.setThumbnail("http://purepng.com/public/uploads/large/playerunknowns-battlegrounds-female-agentpubg-2ib.png");
            result.setTitle("New PUBG Applicant");
            result.addField("Discord Name", apps.get(0).getDiscordName(), true);
            result.addField("Steam Name", apps.get(0).getSteamName(), true);
            result.addField("Stats Link", apps.get(0).getStatsLink(), true);
            result.addField("Steam Link", apps.get(0).getSteamLink(), true);
            result.addField("Age", apps.get(0).getAge(), true);
            result.addField("Hours Played", apps.get(0).getHoursPlayed(), true);
            if (apps.get(0).getOrigin() != null)
                result.addField("Origin", apps.get(0).getOrigin(), true);
            if (apps.get(0).getServer() != null)
                result.addField("Server Preference", apps.get(0).getServer(), true);
            if (apps.get(0).getPlaystyle() != null)
                result.addField("Playstyle", apps.get(0).getPlaystyle(), true);
            if (apps.get(0).getExperience() != null)
                result.addField("Previous Experience", apps.get(0).getExperience(), true);
            if (apps.get(0).getGoals() != null)
                result.addField("Goals With Sway", apps.get(0).getGoals(), true);
            if (apps.get(0).getAnythingElse() != null)
                result.addField("Other Comments", apps.get(0).getAnythingElse(), true);
            result.addField("Role Given", addRole(event, PUBG_ROLE_ID, apps.get(0).getDiscordName()) ? "Yes" : "No", true);
            return result;
        }
        return null;
    }

    private EmbedBuilder getFortniteApplicants(Event event)
    {
        List<FortniteApplicant> apps = JotformParser.getFortniteapplicants();
        if (apps.size() > 0)
        {
            addRole(event, FORTNITE_ROLE_ID, apps.get(0).getDiscordName());
            EmbedBuilder result = new EmbedBuilder();
            result.setColor(Color.green);
            result.setThumbnail("https://pngimage.net/wp-content/uploads/2018/06/fortnite-character-png-9.png");
            result.setTitle("New Fortnite Applicant");
            result.addField("Discord Name", apps.get(0).getDiscordName(), true);
            result.addField("Stream URL", apps.get(0).getStreamName(), true);
            result.addField("Age", apps.get(0).getAge(), true);
            if (apps.get(0).getEpicName() != null)
                result.addField("Epic Name", apps.get(0).getEpicName(), true);
            result.addField("Fortnite Tracker", apps.get(0).getFortniteTracker(), true);
            result.addField("Wins", apps.get(0).getWins(), true);
            if (apps.get(0).getWinPercentage() != null)
                result.addField("Win Percentage", apps.get(0).getWinPercentage(), true);
            if (apps.get(0).getKillDeath() != null)
                result.addField("Kill Death Ratio", apps.get(0).getKillDeath(), true);
            if (apps.get(0).getOrigin() != null)
                result.addField("Origin", apps.get(0).getOrigin(), true);
            if (apps.get(0).getStrongestAsset() != null)
                result.addField("Strongest Asset", apps.get(0).getStrongestAsset(), true);
            if (apps.get(0).getWeakestAsset() != null)
                result.addField("Weakest Asset", apps.get(0).getWeakestAsset(), true);
            if (apps.get(0).getScrimmageExperience() != null)
                result.addField("Scrimmage Experience", apps.get(0).getScrimmageExperience(), true);
            if (apps.get(0).getExperience() != null)
                result.addField("Previous Experience", apps.get(0).getExperience(), true);
            if (apps.get(0).getGoals() != null)
                result.addField("Goals With Sway", apps.get(0).getGoals(), true);
            if (apps.get(0).getServer() != null)
                result.addField("Server Preference", apps.get(0).getServer(), true);
            if (apps.get(0).getAnythingElse() != null)
                result.addField("Other Comments", apps.get(0).getAnythingElse(), true);
            result.addField("Role Given", addRole(event, FORTNITE_ROLE_ID, apps.get(0).getDiscordName()) ? "Yes" : "No", true);
            return result;
        }
        return null;
    }


    private EmbedBuilder getApexApplicants(Event event)
    {
        List<ApexApplicant> apps = JotformParser.getApexapplicants();
        if (apps.size() > 0)
        {
            addRole(event, APEX_ROLE_ID, apps.get(0).getDiscordName());
            EmbedBuilder result = new EmbedBuilder();
            result.setColor(Color.green);
            result.setThumbnail("http://purepng.com/public/uploads/large/apex-legends-icon-high-resolution-scy.png");
            result.setTitle("New Apex Legends Applicant");
            result.addField("Discord Name", apps.get(0).getDiscordName(), true);
            result.addField("Origin Name", apps.get(0).getOriginName(), true);
            result.addField("Age", apps.get(0).getAge(), true);
            if (apps.get(0).getOrigin() != null)
                result.addField("Origin", apps.get(0).getOrigin(), true);
            result.addField("Hours Played", apps.get(0).getHoursPlayed(), true);
            if (apps.get(0).getBRExperience() != null)
                result.addField("Battle Royale Experience", apps.get(0).getBRExperience(), true);
            if (apps.get(0).getExperience() != null)
                result.addField("Previous Experience", apps.get(0).getExperience(), true);
            if (apps.get(0).getStrongestAsset() != null)
                result.addField("Strongest Asset", apps.get(0).getStrongestAsset(), true);
            if (apps.get(0).getGoals() != null)
                result.addField("Goals With Sway", apps.get(0).getGoals(), true);
            if (apps.get(0).getAnythingElse() != null)
                result.addField("Other Comments", apps.get(0).getAnythingElse(), true);
            result.addField("Role Given", addRole(event, APEX_ROLE_ID, apps.get(0).getDiscordName()) ? "Yes" : "No", true);
            return result;
        }
        return null;
    }

}
