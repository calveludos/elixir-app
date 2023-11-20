package com.teamvectora.elixirapi.manager;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JsonMangerTest {

    @Test
    void read() {
    }

    @Test
    void get1() throws IOException, ParseException {
        String[] path = {"cleric", "Especialização", "Cultista", "Habilidades", "Nível 8"};
        String habilite = (String) JsonManger.get("class", path);
        assertEquals("A partir do 8° nível, o cultista pode optar, ao invés de afastar automaticamente um morto-vivo, por controlá-lo por até 24 horas. Findo esse período, o morto-vivo não estará mais sob o controle do cultista.", habilite);
    }

    @Test
    void get12() throws IOException, ParseException {
        String habilite = (String) JsonManger.get("class/cleric/Especialização/Cultista/Habilidades/Nível 8");
        assertEquals("A partir do 8° nível, o cultista pode optar, ao invés de afastar automaticamente um morto-vivo, por controlá-lo por até 24 horas. Findo esse período, o morto-vivo não estará mais sob o controle do cultista.", habilite);
    }

    @Test
    void get2() throws IOException, ParseException {
        List<Object> path = new ArrayList<>();
        path.add("cleric");
        path.add(new Tuple<>("level", 7));
        path.add("XP");
        long XP = (long) JsonManger.get("class", path);
        assertEquals(48000, XP);
    }

    @Test
    void get22() throws IOException, ParseException {
        long XP = (long) JsonManger.get("class/cleric/level:7/XP");
        assertEquals(48000, XP);
    }

    @Test
    void get222() throws IOException, ParseException {
        String s = (String) JsonManger.get("languages/languages:1/name");
        assertEquals("Anão", s);
    }

    @Test
    void get2221() throws IOException, ParseException {
        JSONObject s = (JSONObject) JsonManger.get("weapons/weapons:1");
        assertEquals("Adaga", s.get("name"));
    }

}
