public class RLApplicant {
    private String discordName;
    private String steamName;
    private String steamLink;
    private String age;
    private String rank;
    private String highestRank;
    private String hoursPlayed;
    private String MMR;
    private String origin;
    private String server;
    private String playstyle;
    private String experience;
    private String strongestAsset;
    private String needsImprovement;
    private String goals;
    private String anythingElse;

    public RLApplicant() {
    }

    public RLApplicant(String discordName, String steamName, String steamLink, String age, String rank, String highestRank, String hoursPlayed) {
        this.discordName = discordName;
        this.steamName = steamName;
        this.steamLink = steamLink;
        this.age = age;
        this.rank = rank;
        this.highestRank = highestRank;
        this.hoursPlayed = hoursPlayed;
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

    public String getRank() {
        return rank;
    }

    public String getHighestRank() {
        return highestRank;
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

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setHighestRank(String highestRank) {
        this.highestRank = highestRank;
    }

    public void setHoursPlayed(String hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public String getMMR() {
        return MMR;
    }

    public void setMMR(String MMR) {
        this.MMR = MMR;
    }

    public String getAnythingElse() {
        return anythingElse;
    }

    public void setAnythingElse(String anythingElse) {
        this.anythingElse = anythingElse;
    }

    public String getStrongestAsset() {
        return strongestAsset;
    }

    public void setStrongestAsset(String strongestAsset) {
        this.strongestAsset = strongestAsset;
    }

    public String getNeedsImprovement() {
        return needsImprovement;
    }

    public void setNeedsImprovement(String needsImprovement) {
        this.needsImprovement = needsImprovement;
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
