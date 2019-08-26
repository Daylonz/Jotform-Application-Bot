public class ApexApplicant {

    private String discordName;
    private String originName;
    private String age;
    private String origin;
    private String hoursPlayed;
    private String BRExperience;
    private String experience;
    private String strongestAsset;
    private String goals;
    private String anythingElse;

    public ApexApplicant() {
    }

    public ApexApplicant(String discordName, String age) {
        this.discordName = discordName;
        this.age = age;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(String hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public String getBRExperience() {
        return BRExperience;
    }

    public void setBRExperience(String BRExperience) {
        this.BRExperience = BRExperience;
    }

    public String getStrongestAsset() {
        return strongestAsset;
    }

    public void setStrongestAsset(String strongestAsset) {
        this.strongestAsset = strongestAsset;
    }

    public String getDiscordName() {
        return discordName;
    }

    public String getAge() {
        return age;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAnythingElse() {
        return anythingElse;
    }

    public void setAnythingElse(String anythingElse) {
        this.anythingElse = anythingElse;
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

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}
