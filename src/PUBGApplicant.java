public class PUBGApplicant {
    private String discordName;
    private String steamName;
    private String statsLink;
    private String steamLink;
    private String age;
    private String hoursPlayed;
    private String origin;
    private String server;
    private String playstyle;
    private String experience;
    private String goals;
    private String anythingElse;

    public PUBGApplicant() {
    }

    public PUBGApplicant(String discordName, String steamName, String statsLink, String steamLink, String age, String hoursPlayed) {
        this.discordName = discordName;
        this.steamName = steamName;
        this.statsLink = statsLink;
        this.steamLink = steamLink;
        this.age = age;
        this.hoursPlayed = hoursPlayed;
    }

    public String getStatsLink() {
        return statsLink;
    }

    public void setStatsLink(String statsLink) {
        this.statsLink = statsLink;
    }

    public String getDiscordName() {
        return discordName;
    }

    public String getSteamName() {
        return steamName;
    }

    public String getSteamLink() {
        return steamLink;
    }

    public String getAge() {
        return age;
    }

    public String getHoursPlayed() {
        return hoursPlayed;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public void setSteamName(String steamName) {
        this.steamName = steamName;
    }

    public void setSteamLink(String steamLink) {
        this.steamLink = steamLink;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setHoursPlayed(String hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public String getAnythingElse() {
        return anythingElse;
    }

    public void setAnythingElse(String anythingElse) {
        this.anythingElse = anythingElse;
    }

    public String getPlaystyle() {
        return playstyle;
    }

    public void setPlaystyle(String playstyle) {
        this.playstyle = playstyle;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}
