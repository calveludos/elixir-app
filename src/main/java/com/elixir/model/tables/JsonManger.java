package com.elixir.model.tables;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class JsonManger {
    final static FileReader pathClass;
    final static FileReader pathRace;

    static {
        try {
            pathClass = new FileReader("src/main/java/com/elixir/model/tables/class.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            pathRace = new FileReader("src/main/java/com/elixir/model/tables/race.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Race.Class readRaceData(int id) {
        try (Reader reader = pathRace) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Race.Class>>() {}.getType();
            List<Race.Class> objets = gson.fromJson(reader, listType);

            return objets.get(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Race.Language readLanguagesData(int id) {
        try (Reader reader = pathRace) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Race.Language>>() {}.getType();
            List<Race.Language> languagesList = gson.fromJson(reader, listType);

            return languagesList.get(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Race.Alignment readAlignmentsData(int id) {
        try (Reader reader = pathRace) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Race.Alignment>>() {}.getType();
            List<Race.Alignment> alignmentsList = gson.fromJson(reader, listType);

            return alignmentsList.get(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClassLevels.Cleric readClericData(int id) {
        try (Reader reader = pathClass) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List< ClassLevels.Cleric>>() {}.getType();
            List<ClassLevels.Cleric> clericList = gson.fromJson(reader, listType);

            if (id >= 0 && id < clericList.size()) {
                return clericList.get(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClassLevels.Warrior readWarriorData(int id) {
        try (Reader reader = pathClass) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List< ClassLevels.Warrior>>() {}.getType();
            List<ClassLevels.Warrior> warriorList = gson.fromJson(reader, listType);

            if (id >= 0 && id < warriorList.size()) {
                return warriorList.get(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClassLevels.Wizard readWizardData(int id) {
        try (Reader reader = pathClass) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List< ClassLevels.Wizard>>() {}.getType();
            List<ClassLevels.Wizard> wizardList = gson.fromJson(reader, listType);

            if (id >= 0 && id < wizardList.size()) {
                return wizardList.get(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClassLevels.Thief readThiefData(int id) {
        try (Reader reader = pathClass) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List< ClassLevels.Thief>>() {}.getType();
            List<ClassLevels.Thief> thiefList = gson.fromJson(reader, listType);

            if (id >= 0 && id < thiefList.size()) {
                return thiefList.get(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
