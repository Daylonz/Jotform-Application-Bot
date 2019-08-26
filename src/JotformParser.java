import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.jotform.api.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class JotformParser {

    private static List<RLApplicant> applicants = new ArrayList<>();
    private static List<RLApplicant> gcapplicants = new ArrayList<>();
    private static List<RLApplicant> c3applicants = new ArrayList<>();
    private static List<RLApplicant> c2applicants = new ArrayList<>();
    private static List<RLApplicant> c1applicants = new ArrayList<>();
    private static List<PUBGApplicant> pubgapplicants = new ArrayList<>();
    private static List<FortniteApplicant> fortniteapplicants = new ArrayList<>();
    private static List<ApexApplicant> apexapplicants = new ArrayList<>();
    private static DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    static JotForm client = new JotForm("API_KEY");

    public static List<RLApplicant> getApplicants() {
        return applicants;
    }

    public static List<RLApplicant> getGCapplicants() {
        List<RLApplicant> result = gcapplicants;
        gcapplicants.clear();
        return result;
    }

    public static List<RLApplicant> getC3applicants() {
        List<RLApplicant> result = c3applicants;
        c3applicants.clear();
        return result;
    }

    public static List<RLApplicant> getC2applicants() {
        List<RLApplicant> result = c2applicants;
        c2applicants.clear();
        return result;
    }

    public static List<RLApplicant> getC1applicants() {
        List<RLApplicant> result = c1applicants;
        c1applicants.clear();
        return result;
    }

    public static List<PUBGApplicant> getPUBGapplicants() {
        List<PUBGApplicant> result = pubgapplicants;
        pubgapplicants.clear();
        return result;
    }

    public static List<FortniteApplicant> getFortniteapplicants() {
        List<FortniteApplicant> result = fortniteapplicants;
        fortniteapplicants.clear();
        return result;
    }

    public static List<ApexApplicant> getApexapplicants() {
        List<ApexApplicant> result = apexapplicants;
        apexapplicants.clear();
        return result;
    }

    public static void checkRLApplicants(Date date) {
        try {
            applicants = new ArrayList<>();
            gcapplicants = new ArrayList<>();
            c3applicants = new ArrayList<>();
            c2applicants = new ArrayList<>();
            c1applicants = new ArrayList<>();
            HashMap<String, String> filter = new HashMap<>();
            filter.put("created_at:gt", dtf.format(date));
            JSONObject form = client.getFormSubmissions(72806248300148L, "0", "1000", filter, "created_at");
            if (form != null) {
                JSONArray content = new JSONArray(form.get("content").toString());
                if (content.toString().length() > 5)
                {
                    System.out.println("RL Contents: " + content.toString());
                }
                for (int i = 0; i < content.length(); i++) {
                    RLApplicant rlapp = new RLApplicant();
                    JSONObject answers = new JSONObject(content.getJSONObject(i).get("answers").toString());
                    Iterator<String> keys = answers.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (answers.get(key) instanceof JSONObject) {
                            JSONObject page = new JSONObject(answers.get(key).toString());
                            String question = page.get("name").toString();
                            switch (question) {
                                case "discordName":
                                    if (page.has("answer"))
                                        rlapp.setDiscordName(page.get("answer").toString());
                                    break;
                                case "steamName":
                                    if (page.has("answer"))
                                        rlapp.setSteamName(page.get("answer").toString());
                                    break;
                                case "steamProfile":
                                    if (page.has("answer"))
                                        rlapp.setSteamLink(page.get("answer").toString());
                                    break;
                                case "age":
                                    if (page.has("answer"))
                                        rlapp.setAge(page.get("answer").toString());
                                    break;
                                case "currentRank":
                                    if (page.has("answer"))
                                        rlapp.setRank(page.get("answer").toString());
                                    break;
                                case "highestRank":
                                    if (page.has("answer"))
                                        rlapp.setHighestRank(page.get("answer").toString());
                                    break;
                                case "hoursPlayed":
                                    if (page.has("answer"))
                                        rlapp.setHoursPlayed(page.get("answer").toString());
                                    break;
                                case "mmr":
                                    if (page.has("answer"))
                                        rlapp.setMMR(page.get("answer").toString());
                                    break;
                                case "anythingElse":
                                    if (page.has("answer"))
                                        rlapp.setAnythingElse(page.get("answer").toString());
                                    break;
                                case "whatDo":
                                    if (page.has("answer"))
                                        rlapp.setStrongestAsset(page.get("answer").toString());
                                    break;
                                case "whatSkills":
                                    if (page.has("answer"))
                                        rlapp.setNeedsImprovement(page.get("answer").toString());
                                    break;
                                case "whereOn":
                                    if (page.has("answer"))
                                        rlapp.setPlaystyle(page.get("answer").toString());
                                    break;
                                case "stateprovincecountryplanet":
                                    if (page.has("answer"))
                                        rlapp.setOrigin(page.get("answer").toString());
                                    break;
                                case "whatPrevious":
                                    if (page.has("answer"))
                                        rlapp.setExperience(page.get("answer").toString());
                                    break;
                                case "whatServer":
                                    if (page.has("answer"))
                                        rlapp.setServer(page.get("answer").toString());
                                    break;
                                case "whatAre10":
                                    if (page.has("answer"))
                                        rlapp.setGoals(page.get("answer").toString());
                                    break;

                            }
                        }
                    }
                    applicants.add(rlapp);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (RLApplicant a : applicants)
        {
            switch (a.getRank())
            {
                case "Grand Champion":
                    gcapplicants.add(a);
                    break;
                case "Champion 3":
                    c3applicants.add(a);
                    break;
                case "Champion 2":
                    c2applicants.add(a);
                    break;
                case "Champion 1":
                    c1applicants.add(a);
                    break;
            }
        }
    }

    public static void checkPUBGApplicants(Date date) {
        try {
            pubgapplicants = new ArrayList<>();
            HashMap<String, String> filter = new HashMap<>();
            filter.put("created_at:gt", dtf.format(date));
            JSONObject form = client.getFormSubmissions(80858184698173L, "0", "1000", filter, "created_at");
            if (form != null) {
                JSONArray content = new JSONArray(form.get("content").toString());
                if (content.toString().length() > 5)
                {
                    System.out.println("PUBG Contents: " + content.toString());
                }
                for (int i = 0; i < content.length(); i++)
                {
                    PUBGApplicant app = new PUBGApplicant();
                    JSONObject answers = new JSONObject(content.getJSONObject(i).get("answers").toString());
                    Iterator<String> keys = answers.keys();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        if (answers.get(key) instanceof JSONObject) {
                            JSONObject page = new JSONObject(answers.get(key).toString());
                            String question = page.get("name").toString();
                            switch (question)
                            {
                                case "discordName":
                                    if (page.has("answer"))
                                        app.setDiscordName(page.get("answer").toString());
                                    break;
                                case "steamName":
                                    if (page.has("answer"))
                                        app.setSteamName(page.get("answer").toString());
                                    break;
                                case "linkTo":
                                    if (page.has("answer"))
                                        app.setStatsLink(page.get("answer").toString());
                                    break;
                                case "steamProfile":
                                    if (page.has("answer"))
                                        app.setSteamLink(page.get("answer").toString());
                                    break;
                                case "age":
                                    if (page.has("answer"))
                                        app.setAge(page.get("answer").toString());
                                    break;
                                case "hoursPlayed":
                                    if (page.has("answer"))
                                        app.setHoursPlayed(page.get("answer").toString());
                                    break;
                                case "anythingElse":
                                    if (page.has("answer"))
                                        app.setAnythingElse(page.get("answer").toString());
                                    break;
                                case "whereOn":
                                    if (page.has("answer"))
                                        app.setPlaystyle(page.get("answer").toString());
                                    break;
                                case "stateprovincecountryplanet":
                                    if (page.has("answer"))
                                        app.setOrigin(page.get("answer").toString());
                                    break;
                                case "whatPrevious":
                                    if (page.has("answer"))
                                        app.setExperience(page.get("answer").toString());
                                    break;
                                case "whatServer":
                                    if (page.has("answer"))
                                        app.setServer(page.get("answer").toString());
                                    break;
                                case "wharAre":
                                    if (page.has("answer"))
                                        app.setGoals(page.get("answer").toString());
                                    break;

                            }
                        }
                    }
                    pubgapplicants.add(app);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void checkFortniteApplicants(Date date) {
        try {
            fortniteapplicants = new ArrayList<>();
            HashMap<String, String> filter = new HashMap<>();
            filter.put("created_at:gt", dtf.format(date));
            JSONObject form = client.getFormSubmissions(81408244298158L, "0", "1000", filter, "created_at");
            if (form != null) {
                JSONArray content = new JSONArray(form.get("content").toString());
                if (content.toString().length() > 5)
                {
                    System.out.println("Fortnite Contents: " + content.toString());
                }
                for (int i = 0; i < content.length(); i++)
                {
                    FortniteApplicant app = new FortniteApplicant();
                    JSONObject answers = new JSONObject(content.getJSONObject(i).get("answers").toString());
                    Iterator<String> keys = answers.keys();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        if (answers.get(key) instanceof JSONObject) {
                            JSONObject page = new JSONObject(answers.get(key).toString());
                            String question = page.get("name").toString();
                            switch (question)
                            {
                                case "discordName":
                                    if (page.has("answer"))
                                        app.setDiscordName(page.get("answer").toString());
                                    break;
                                case "streamUrl":
                                    if (page.has("answer"))
                                        app.setStreamName(page.get("answer").toString());
                                    break;
                                case "age":
                                    if (page.has("answer"))
                                        app.setAge(page.get("answer").toString());
                                    break;
                                case "anythingElse":
                                    if (page.has("answer"))
                                        app.setAnythingElse(page.get("answer").toString());
                                    break;
                                case "stateprovincecountryplanet":
                                    if (page.has("answer"))
                                        app.setOrigin(page.get("answer").toString());
                                    break;
                                case "haveYou":
                                    if (page.has("answer"))
                                        app.setExperience(page.get("answer").toString());
                                    break;
                                case "wins":
                                    if (page.has("answer"))
                                        app.setWins(page.get("answer").toString());
                                    break;
                                case "winPercentage":
                                    if (page.has("answer"))
                                        app.setWinPercentage(page.get("answer").toString());
                                    break;

                                case "killdeath":
                                    if (page.has("answer"))
                                        app.setKillDeath(page.get("answer").toString());
                                    break;
                                case "whatDo":
                                    if (page.has("answer"))
                                        app.setStrongestAsset(page.get("answer").toString());
                                    break;
                                case "whatDo22":
                                    if (page.has("answer"))
                                        app.setWeakestAsset(page.get("answer").toString());
                                    break;
                                case "scrimmageExperience":
                                    if (page.has("answer"))
                                        app.setScrimmageExperience(page.get("answer").toString());
                                    break;
                                case "whatServer":
                                    if (page.has("answer"))
                                        app.setServer(page.get("answer").toString());
                                    break;
                                case "epicGames":
                                    if (page.has("answer"))
                                        app.setEpicName(page.get("answer").toString());
                                    break;
                                case "whatAre10":
                                    if (page.has("answer"))
                                        app.setGoals(page.get("answer").toString());
                                    break;
                                case "pleaseLink21":
                                    if (page.has("answer"))
                                        app.setFortniteTracker(page.get("answer").toString());
                                    break;

                            }
                        }
                    }
                    fortniteapplicants.add(app);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void checkApexApplicants(Date date) {
        try {
            apexapplicants = new ArrayList<>();
            HashMap<String, String> filter = new HashMap<>();
            filter.put("created_at:gt", dtf.format(date));
            JSONObject form = client.getFormSubmissions(90394519295164L, "0", "1000", filter, "created_at");
            if (form != null) {
                JSONArray content = new JSONArray(form.get("content").toString());
                if (content.toString().length() > 5)
                {
                    System.out.println("Apex Contents: " + content.toString());
                }
                for (int i = 0; i < content.length(); i++)
                {
                    ApexApplicant app = new ApexApplicant();
                    JSONObject answers = new JSONObject(content.getJSONObject(i).get("answers").toString());
                    Iterator<String> keys = answers.keys();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        if (answers.get(key) instanceof JSONObject) {
                            JSONObject page = new JSONObject(answers.get(key).toString());
                            String question = page.get("name").toString();
                            switch (question)
                            {
                                case "discordName":
                                    if (page.has("answer"))
                                        app.setDiscordName(page.get("answer").toString());
                                    break;
                                case "age":
                                    if (page.has("answer"))
                                        app.setAge(page.get("answer").toString());
                                    break;
                                case "anythingElse":
                                    if (page.has("answer"))
                                        app.setAnythingElse(page.get("answer").toString());
                                    break;
                                case "stateprovincecountryplanet":
                                    if (page.has("answer"))
                                        app.setOrigin(page.get("answer").toString());
                                    break;
                                case "whatPrevious":
                                    if (page.has("answer"))
                                        app.setExperience(page.get("answer").toString());
                                    break;
                                case "whatDo23":
                                    if (page.has("answer"))
                                        app.setStrongestAsset(page.get("answer").toString());
                                    break;
                                case "whatAre10":
                                    if (page.has("answer"))
                                        app.setGoals(page.get("answer").toString());
                                    break;
                                case "whatOther13":
                                    if (page.has("answer"))
                                        app.setBRExperience(page.get("answer").toString());
                                    break;
                                case "hoursPlayed":
                                    if (page.has("answer"))
                                        app.setHoursPlayed(page.get("answer").toString());
                                    break;
                                case "originName":
                                    if (page.has("answer"))
                                        app.setOriginName(page.get("answer").toString());
                                    break;

                            }
                        }
                    }
                    apexapplicants.add(app);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}